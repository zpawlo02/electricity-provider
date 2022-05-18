package com.pawelzielinski.service;

import com.pawelzielinski.model.Customer;
import com.pawelzielinski.model.PowerLimitation;
import com.pawelzielinski.repository.CustomerRepository;
import com.pawelzielinski.repository.PowerLimitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PowerLimitationRepository powerLimitationRepository;

    @Autowired
    private PowerLimitationService powerLimitationService;

    public String addCustomer(Customer customer) {
        if (customer.checkIfAnyValueIsBlankNullOrEmpty() == 0 && customer.getAddress().checkIfAnyValueIsBlankNullOrEmpty() == 0) {

            String customerZipCode = customer.getAddress().getZipCode();
            PowerLimitation powerLimitation = powerLimitationRepository.getByZipCode(customerZipCode);
            if(powerLimitation == null){
                powerLimitation = powerLimitationRepository.getByZipCode(customerZipCode.substring(0, customerZipCode.indexOf('-')).trim());
            }
            if(powerLimitation != null){
                int avaiableValue = powerLimitation.getPowerLimit()-powerLimitation.getUsedPower();
                if(avaiableValue < customer.getKwValue()){
                    return "Dostępne jest tylko: " + avaiableValue+"kW";
                }else {
                    powerLimitation.setUsedPower(powerLimitation.getUsedPower()+customer.getKwValue());
                    try {
                        powerLimitationService.updatePowerLimitation(powerLimitation);
                    }catch (Exception e){
                        return e.getMessage();
                    }

                    customerRepository.save(customer);
                    return "Added customer";
                }
            }else {
                powerLimitation = powerLimitationRepository.getByZipCode("default");
                if(powerLimitation.getPowerLimit() <= customer.getKwValue()){
                    customerRepository.save(customer);
                    return "Added customer";
                }
            }
        }

        return "Sprawdź dane, są niepoprawne!";
    }
}
