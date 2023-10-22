package br.com.rocha.CAR.interfaces;

import br.com.rocha.CAR.domain.Driver;
import br.com.rocha.CAR.domain.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class DriverAPI {

    @Autowired
    DriverRepository driverRepository;

    @GetMapping("/drivers")
    public List<Driver> listDrivers() {
        return driverRepository.findAll();
    }

    @GetMapping("/drivers/{id}")
    public Driver finDriver(@PathVariable("id") Long id) {
        return driverRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/drivers")
    public Driver createDriver(@RequestBody Driver driver){
        return driverRepository.save(driver);
    }

    @PutMapping("/drivers/{id}")
    public Driver fullUpdateDriver(@PathVariable("id")Long id, @RequestBody Driver driver){
        Driver founDriver = finDriver(id);
        founDriver.setBirthDate(driver.getBirthDate());
        founDriver.setName(driver.getName());
        return driverRepository.save(founDriver);
    }

    @PatchMapping("/drivers/{id}")
    public Driver incrementalUpdateDriver(@PathVariable("id") Long id, @RequestBody Driver driver){
        Driver foundDriver = finDriver(id);
        foundDriver.setBirthDate(Optional.ofNullable(driver.getBirthDate()).orElse(foundDriver.getBirthDate()));
        foundDriver.setName(Optional.ofNullable(driver.getName()).orElse(foundDriver.getName()));
        return driverRepository.save(foundDriver);
    }

    @DeleteMapping("/drivers/{id}")
    public void deleteDriver(@PathVariable("id")Long id){
        driverRepository.deleteById(id);
    }


}