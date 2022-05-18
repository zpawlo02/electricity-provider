package com.pawelzielinski.service;

import com.pawelzielinski.model.PowerLimitation;
import com.pawelzielinski.repository.PowerLimitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PowerLimitationService {

    @Autowired
    private PowerLimitationRepository powerLimitationRepository;

    public void addPowerLimitation(PowerLimitation powerLimitation){
        powerLimitationRepository.save(powerLimitation);
    }
    public void updatePowerLimitation(PowerLimitation powerLimitation){
        addPowerLimitation(powerLimitation); // same code will be used
    }
}
