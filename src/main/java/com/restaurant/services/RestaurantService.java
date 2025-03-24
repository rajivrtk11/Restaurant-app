package com.restaurant.services;

import com.restaurant.entity.Restaurant;
import com.restaurant.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant createRestaurant(Restaurant restaurant) {
        restaurant.setCreatedOn(new Date().toInstant());
        return restaurantRepository.save(restaurant);
    }

    public Page<Restaurant> getRestaurantByPincodes(String pincode, Pageable pageable) {
        return restaurantRepository.findByPincode(pincode, pageable);
    }

    public Restaurant getRestaurantById(Long id) {
        Optional<Restaurant> byId = restaurantRepository.findById(id);
        if(byId.isEmpty()) throw new IllegalArgumentException("Restaurant with this id doest not exist ");

        return byId.get();
    }
}
