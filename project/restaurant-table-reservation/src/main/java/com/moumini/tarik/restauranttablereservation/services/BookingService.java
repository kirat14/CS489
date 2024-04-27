package com.moumini.tarik.restauranttablereservation.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.moumini.tarik.restauranttablereservation.dtos.BookingDto;
import com.moumini.tarik.restauranttablereservation.models.Booking;
import com.moumini.tarik.restauranttablereservation.models.BookingStatus;
import com.moumini.tarik.restauranttablereservation.models.Customer;
import com.moumini.tarik.restauranttablereservation.models.RestaurantTable;
import com.moumini.tarik.restauranttablereservation.payload.response.MessageResponse;
import com.moumini.tarik.restauranttablereservation.repositories.BookingRepository;
import com.moumini.tarik.restauranttablereservation.repositories.TableRepository;
import com.moumini.tarik.restauranttablereservation.security.services.UserDetailsImpl;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TableRepository restaurantTableRepository;

    // Needed to Cancel bookings after 5 minutes if not paid
    @Autowired
    private TaskExecutor taskExecutor;

    // Get Booking History for a Customer
    public ResponseEntity<List<BookingDto>> getCustomerBookingHistory() {
        // Get the currently logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long customerId = userDetails.getId();

        List<Booking> bookings = bookingRepository.findByCustomer_Id(customerId);
        List<BookingDto> bookingDtos = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingDtos.add(convertBookingToDto(booking));
        }
        return ResponseEntity.ok(bookingDtos);
    }

    private BookingDto convertBookingToDto(Booking booking) {
        RestaurantTable table = booking.getRestaurantTable();
        Customer customer = booking.getCustomer();
        return new BookingDto(table.getId(),
        booking.getBookedDateTime(),
        booking.getArrivalDateTime(),
        customer.getFirstName(),
        customer.getLastName());
    }

    // Create a booking
    public ResponseEntity<?> createBooking(Booking booking) {
        // Check if the table exists
        Optional<RestaurantTable> table = restaurantTableRepository.findById(booking.getRestaurantTable().getId());
        if (!table.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        booking.setBookedDateTime(LocalDateTime.now());
        // Validate booking based on arrival time
        if (booking.getArrivalDateTime() == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Arrival and booking times are required"));
        }

        LocalDate arrivalDate = booking.getArrivalDateTime().toLocalDate();
        LocalDate bookedDate = booking.getBookedDateTime().toLocalDate();

        if (Period.between(bookedDate, arrivalDate).getDays() < 1) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Arrival time must be at least 24 hours After booking time"));
        }

        // Check table availability for the requested time
        if (bookingRepository.existsByRestaurantTableAndBookedDateTime(table.get(), booking.getBookedDateTime())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Table already booked for this time"));
        }

        // Get the currently logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Customer customer = new Customer();
        customer.setId(userDetails.getId());
        booking.setCustomer(customer); // Set the customer for the booking

        // Set the table reference (avoid potential lazy loading issues)
        booking.setRestaurantTable(table.get());
        booking.setStatus(BookingStatus.NEW);
        Booking savedBooking = bookingRepository.save(booking);
        scheduleBookingCancellation(savedBooking); // Schedule cancellation task
        return ResponseEntity.ok(savedBooking);
    }

    // Read a booking by ID
    public ResponseEntity<Booking> getBookingById(Integer id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (!booking.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(booking.get());
    }

    // Update a booking
    public ResponseEntity<?> updateBooking(Integer id, Booking bookingDetails) {
        Booking existingBooking = getBookingById(id).getBody(); // Assuming successful retrieval

        // Update relevant fields (avoid overwriting everything)
        existingBooking.setBookedDateTime(bookingDetails.getBookedDateTime());
        existingBooking.setArrivalDateTime(bookingDetails.getArrivalDateTime());

        // Re-check table availability if booked time changed
        if (!existingBooking.getBookedDateTime().equals(bookingDetails.getBookedDateTime())) {
            if (bookingRepository.existsByRestaurantTableAndBookedDateTime(existingBooking.getRestaurantTable(),
                    bookingDetails.getBookedDateTime())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Table already booked for this time"));
            }
        }

        Booking savedBooking = bookingRepository.save(existingBooking);
        scheduleBookingCancellation(savedBooking); // Schedule cancellation task
        return ResponseEntity.ok(savedBooking);
    }

    // Delete a booking
    public ResponseEntity<?> deleteBooking(Integer id) {
        bookingRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // No content to return on successful deletion
    }

    public ResponseEntity<?> cancelBooking(int id) {
        Booking existingBooking = getBookingById(id).getBody();
        if (!existingBooking.canCancel()) {
            return ResponseEntity.badRequest().body(new MessageResponse("You can not cancel this booking (24hours)"));
        }
        existingBooking.setStatus(BookingStatus.CANCELLED);
        Booking savedBooking = bookingRepository.save(existingBooking);
        BookingDto bookingDto = new BookingDto(savedBooking.getRestaurantTable().getId(),
                savedBooking.getBookedDateTime(), savedBooking.getArrivalDateTime(),
                savedBooking.getCustomer().getFirstName(), savedBooking.getCustomer().getLastName());
        return ResponseEntity.ok(bookingDto);
    }

    private void scheduleBookingCancellation(Booking booking) {
        long cancellationDelay = 1 * 60 * 1000; // 5 minutes in milliseconds
        taskExecutor.execute(() -> {
            try {
                Thread.sleep(cancellationDelay);
                Optional<Booking> unpaidBooking = bookingRepository.findById(booking.getId());
                if (unpaidBooking.isPresent() && unpaidBooking.get().getStatus().equals(BookingStatus.NEW)) {
                    unpaidBooking.get().setStatus(BookingStatus.CANCELLED);
                    bookingRepository.save(unpaidBooking.get());
                    System.out.println("Booking cancelled after 5 minutes");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
