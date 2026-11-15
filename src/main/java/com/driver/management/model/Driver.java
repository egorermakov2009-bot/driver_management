package com.driver.management.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    private String licenseNumber;

    private LocalDate dateOfBirth;

    public Driver() {
    }

    public Driver(String firstName, String lastName,
                  String email, String phone,
                  String licenseNumber, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.licenseNumber = licenseNumber;
        this.dateOfBirth = dateOfBirth;
    }

}
