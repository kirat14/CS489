package com.moumini.tarik.restauranttablereservation.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @PostMapping("/book")
    public String add() {
        return "<h1>Reserve a table</h1>";
    }
    @GetMapping("/{id}")
    public String read() {
        return "<h1>Your reservation</h1>";
    }

    @GetMapping("/cancel/{id}")
    public String remove() {
        return "<h1>Cancel reservation</h1>";
    }
    @PutMapping("/{id}")
    public String edit() {
        return "<h1>Edit Reservation</h1>";
    }

}
