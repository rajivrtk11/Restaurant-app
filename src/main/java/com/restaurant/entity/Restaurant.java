package com.restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "restaurant")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    @Column(columnDefinition = "TEXT[]")
    String[] serviceAblePincode;

    @Column
    Instant createdOn;

    public Restaurant(Long id, String name, String[] serviceAblePincode) {
        this.name = name;
        this.id = id;
        this.serviceAblePincode = serviceAblePincode;
    }
}
