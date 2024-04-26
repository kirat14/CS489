package com.moumini.tarik.restauranttablereservation.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/main")
    public String home(Authentication a) {
        return "Hello, " + a.getName();
    }
}
