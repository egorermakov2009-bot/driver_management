package com.driver.management.controller;


import com.driver.management.dto.DriverRequest;
import com.driver.management.model.Driver;
import com.driver.management.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@CrossOrigin(origins = {
        "http://localhost:5173"
})
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @GetMapping("/{id}")
    public Driver getDriverById(@PathVariable Long id) {
        return driverService.getDriverById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Driver createDriver(@Valid @RequestBody DriverRequest request) {

        Driver driver = new Driver(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhone(),
                request.getLicenseNumber(),
                request.getDateOfBirth()
        );
        return driverService.createDriver(driver);
    }

    @PutMapping("/{id}")
    public Driver updateDriver(
            @PathVariable Long id,
            @Valid @RequestBody DriverRequest request) {

        Driver driver = convertToDriver(request);
        return driverService.updateDriver(id, driver);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
    }

    private Driver convertToDriver(DriverRequest request) {
        return new Driver(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhone(),
                request.getLicenseNumber(),
                request.getDateOfBirth()
        );
    }

}
