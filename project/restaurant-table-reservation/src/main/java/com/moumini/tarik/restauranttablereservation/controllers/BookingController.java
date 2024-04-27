package com.moumini.tarik.restauranttablereservation.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moumini.tarik.restauranttablereservation.models.Booking;
import com.moumini.tarik.restauranttablereservation.services.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {

  @Autowired
  private BookingService bookingService;

  // Create a booking (POST /booking/book)
  @PostMapping("/book")
  public ResponseEntity<?> createBooking(@Valid @RequestBody Booking booking) {
      return bookingService.createBooking(booking);
  }

  // Read a booking by ID (GET /booking/{id})
  @GetMapping("/{id}")
  public ResponseEntity<?> getBookingById(@PathVariable Integer id) {
      return bookingService.getBookingById(id);
  }

  // Read a booking by ID (GET /booking/{id})
  @GetMapping("/customer")
  public ResponseEntity<?> getCustomerBookingHistory() {
      return bookingService.getCustomerBookingHistory();
  }

  // Update a booking (PUT /booking/{id})
  @PutMapping("/{id}")
  public ResponseEntity<?> updateBooking(@PathVariable Integer id, @Valid @RequestBody Booking bookingDetails) {
    return bookingService.updateBooking(id, bookingDetails);
  }

  @PutMapping("/cancel/{id}")
  public ResponseEntity<?> cancelBooking(@PathVariable Integer id) {
    return bookingService.cancelBooking(id);
  }
  // Delete a booking (DELETE /booking/cancel/{id}) - Assuming a separate endpoint for cancellation
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteBooking(@PathVariable Integer id) {
    return bookingService.deleteBooking(id);
  }
}

