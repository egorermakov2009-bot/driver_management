package com.driver.management.service;

import com.driver.management.exception.DriverAlreadyExistsException;
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

        if (driverRepository.existsByEmailIgnoreCase(driver.getEmail())) {
            throw new DriverAlreadyExistsException(
                    "A Driver with this email already exists"
            );
        }
        return driverRepository.save(driver);
    }

    public Driver updateDriver(Long id, Driver updatedDriver) {
        Driver existingDriver = driverRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Driver not found with id: " + id
                ));

        boolean emailUsed = driverRepository
                .existsByEmailIgnoreCase(updatedDriver.getEmail());

        if (emailUsed && !existingDriver.getEmail()
                .equalsIgnoreCase(updatedDriver.getEmail())) {
            throw new DriverAlreadyExistsException(
                    "A driver with this email already exists."
            );
        }

        existingDriver.setFirstName(updatedDriver.getFirstName());
        existingDriver.setLastName(updatedDriver.getLastName());
        existingDriver.setEmail(updatedDriver.getEmail());
        existingDriver.setPhone(updatedDriver.getPhone());
        existingDriver.setLicenseNumber(updatedDriver.getLicenseNumber());
        existingDriver.setDateOfBirth(updatedDriver.getDateOfBirth());

        return driverRepository.save(existingDriver);
    }

    public void deleteDriver(Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Driver not found with id: " + id
                ));

        driverRepository.delete(driver);
    }

}
