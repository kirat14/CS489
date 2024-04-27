package com.moumini.tarik.restauranttablereservation.repositories;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moumini.tarik.restauranttablereservation.models.Booking;
import com.moumini.tarik.restauranttablereservation.models.Customer;
import com.moumini.tarik.restauranttablereservation.models.RestaurantTable;



@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

  // Find bookings by a specific table and date
  List<Booking> findByRestaurantTableAndArrivalDateTime(RestaurantTable table, LocalDateTime arrivalDateTime);

  // Find bookings by a specific customer
  List<Booking> findByCustomer(Customer customer);

  // Find bookings by a specific customer ID (recommended)
  List<Booking> findByCustomer_Id(Long customerId);

  // Check availability for a specific table on a date
  boolean existsByRestaurantTableAndBookedDateTime(
    RestaurantTable table, LocalDateTime arrivalDateTime);
}
