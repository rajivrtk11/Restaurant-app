package com.restaurant.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Home {
    @GetMapping
    public String home() {
        return "Hello world";
    }
}
