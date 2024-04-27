package com.moumini.tarik.restauranttablereservation.dtos;
import java.time.LocalDateTime;

public record BookingDto(int restaurantTableId,
        LocalDateTime bookedDateTime,
        LocalDateTime arrivalDateTime,
        String firstName,
        String lastName) {

}
