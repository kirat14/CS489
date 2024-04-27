package com.moumini.tarik.restauranttablereservation.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.CascadeType;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "bookings")
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.REMOVE})
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_table_id", nullable = false)
    private RestaurantTable restaurantTable;

    private LocalDateTime bookedDateTime;

    @NotNull
    private LocalDateTime arrivalDateTime;

    @NotNull
    private int partySize;

    public Booking(BookingStatus status, Customer customer, RestaurantTable restaurantTable,
             LocalDateTime arrivalDateTime, int partySize) {
        this.status = status;
        this.customer = customer;
        this.restaurantTable = restaurantTable;
        this.arrivalDateTime = arrivalDateTime;
        this.partySize = partySize;
    }

    // Additional methods (optional)
    public boolean canCancel() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime cancellationDeadline = bookedDateTime.plusHours(24);
        return status.equals(BookingStatus.NEW)
                || (status.equals(BookingStatus.CONFIRMED) && now.isBefore(cancellationDeadline));
    }
}
