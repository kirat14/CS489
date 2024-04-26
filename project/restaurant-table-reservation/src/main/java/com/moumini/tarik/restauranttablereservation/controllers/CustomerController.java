package com.moumini.tarik.restauranttablereservation.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/customers")
public class CustomerController {

    @PostMapping("/add")
    public String add() {
        return "<h1>Add Customer</h1>";
    }
    @GetMapping("/{id}")
    public String read() {
        return "<h1>Show customer</h1>";
    }

    @GetMapping("/remove/{id}")
    public String remove() {
        return "<h1>Remove customer</h1>";
    }
    @PutMapping("/edit/{id}")
    public String edit() {
        return "<h1>Edit customer</h1>";
    }

}
