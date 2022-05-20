package com.pawelzielinski.service;

import com.pawelzielinski.model.PowerLimitation;
import com.pawelzielinski.repository.PowerLimitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PowerLimitationService {

    @Autowired
    private PowerLimitationRepository powerLimitationRepository;

    public PowerLimitation addPowerLimitation(PowerLimitation powerLimitation){
        if(powerLimitation.getZipCode() == null || powerLimitation.getZipCode().isEmpty() || powerLimitation.getZipCode().isBlank()){
            return null;
        }
        return powerLimitationRepository.save(powerLimitation);
    }
    public void updatePowerLimitation(PowerLimitation powerLimitation){
        addPowerLimitation(powerLimitation); // same code will be used
    }

    public PowerLimitation getPowerLimitationByZipCode(String zipCode){
        return powerLimitationRepository.getByZipCode(zipCode);
    }

}
