package com.restaurant.repositories;

import com.restaurant.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query(value = "SELECT * FROM restaurant WHERE :pincode = ANY (service_able_pincode)", nativeQuery = true)
    Page<Restaurant> findByPincode(@Param("pincode") String pincode, Pageable pageable);
}
