package com.restaurant.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "Name cannot be null.")
    @NotEmpty(message = "Name cannot be empty.")
    @Length(min = 4, max = 255, message = "Length should be between 4 to 8.")
    String name;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Task> tasksList;

    @Column(columnDefinition = "TEXT[]")
    String[] roles;

    public Users(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
