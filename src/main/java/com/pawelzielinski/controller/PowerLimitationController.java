package com.pawelzielinski.controller;


import com.pawelzielinski.model.PowerLimitation;
import com.pawelzielinski.repository.PowerLimitationRepository;
import com.pawelzielinski.service.PowerLimitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin
public class PowerLimitationController {

    @Autowired
    private PowerLimitationRepository powerLimitationRepository;

    @Autowired
    private PowerLimitationService powerLimitationService;

    @PostMapping(path = "/addPowerLimitation")
    public ResponseEntity<String> addPowerLimitation(@RequestBody PowerLimitation powerLimitation){

        powerLimitationService.addPowerLimitation(powerLimitation);
        return ResponseEntity.ok("Added power limitation");
    }

    @GetMapping(path = "/powerlimitations")
    public ResponseEntity<?> getAllPowerLimitations(){
        return ResponseEntity.ok(powerLimitationRepository.findAll());
    }

    @GetMapping(path = "/powerLimitationByZipCode")
    public ResponseEntity<PowerLimitation> getPowerLimitationByZipCode(@RequestBody String zipCode){
        return ResponseEntity.ok(powerLimitationService.getPowerLimitationByZipCode(zipCode));
    }

    @PostMapping(path = "/powerLimitationUpdate/{id}")
    public ResponseEntity<String> updatePowerLimitation(@RequestBody PowerLimitation powerLimitation, @PathVariable Integer id){
        powerLimitation.setId(powerLimitationRepository.getById(id).getId());
        powerLimitationRepository.save(powerLimitation);

        return ResponseEntity.ok("Updated power limitation");
    }
}
