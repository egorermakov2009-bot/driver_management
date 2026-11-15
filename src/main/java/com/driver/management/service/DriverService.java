package com.driver.management.service;

import com.driver.management.model.Driver;
import com.driver.management.repository.DriverRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver getDriverById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver not found"));
    }

    public Driver createDriver(Driver driver) {

        if (driverRepository.existsByEmail(driver.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Driver with this email already exists"
            );
        }
        return driverRepository.save(driver);
    }

}
