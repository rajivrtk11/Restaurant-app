package com.restaurant.controllers;

import com.restaurant.entity.Restaurant;
import com.restaurant.services.RestaurantService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/create")
    public ResponseEntity<Restaurant> createRestaurant(
            @RequestBody Restaurant restaurant
    ) {
        Restaurant restaurant1 = restaurantService.createRestaurant(restaurant);
        return new ResponseEntity<>(restaurant1, HttpStatus.CREATED);
    }

    @GetMapping("/getRestaurantByPincodes/{pincode}")
    public Page<Restaurant> getRestaurantByPincodes(
            HttpServletResponse response,
            @PathVariable String pincode, Pageable pageable
    ) {
        Cookie cookie = new Cookie("sessionToken", "yourSecureValue");
        cookie.setMaxAge(60 * 60 * 24); // 1 day
        cookie.setSecure(true); // Only send over HTTPS
        cookie.setHttpOnly(true); // Prevent JavaScript access
        cookie.setPath("/"); // Available for the whole app
        response.addCookie(cookie);

        return restaurantService.getRestaurantByPincodes(pincode, pageable);
    }

    @GetMapping("/getRestaurantById/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }
}
