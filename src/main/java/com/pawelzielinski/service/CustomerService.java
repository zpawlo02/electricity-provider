package com.pawelzielinski.service;

import com.pawelzielinski.model.Customer;
import com.pawelzielinski.model.PowerLimitation;
import com.pawelzielinski.repository.CustomerRepository;
import com.pawelzielinski.repository.CustomerRepositoryImpl;
import com.pawelzielinski.repository.PowerLimitationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PowerLimitationRepository powerLimitationRepository;

    @Autowired
    private PowerLimitationService powerLimitationService;
    @Autowired
    private CustomerRepositoryImpl customerRepositoryImpl = new CustomerRepositoryImpl();

    private static final Logger logger = LoggerFactory.getLogger( CustomerService.class);

    public Customer updateCustomer(Integer id, Customer customer){
        customer.setId(customerRepository.getById(id).getId());
        customer = customerRepository.save(customer);
        logger.info("Użytkownik zaaktualizowany!");
        return customer;
    }

    public void deleteCustomer(Integer id){
        customerRepository.deleteById(id);
        logger.info("Użytkownik usunięty!");
    }

    public List<Customer> getAllCustomers(){
        List<Customer> customerList = customerRepository.findAll();
        if(customerList.size() > 0){
            logger.info("Znaleziono " + customerList.size() + " użytkowników");
            return customerList;
        }
        logger.info("Nie znalzeiono użytkowników!");
        return customerList;
    }

    public List<Customer> getAllByFirstName(String firstName){
        return customerRepositoryImpl.findAllByFirsName(firstName);
    }

    public Customer addCustomer(Customer customer) {
        if (customer.checkIfAnyValueIsBlankNullOrEmpty() == 0 && customer.getAddress().checkIfAnyValueIsBlankNullOrEmpty() == 0) {

            String customerZipCode = customer.getAddress().getZipCode();
            PowerLimitation powerLimitation = powerLimitationRepository.getByZipCode(customerZipCode);
            if(powerLimitation == null){
                powerLimitation = powerLimitationRepository.getByZipCode(customerZipCode.substring(0, customerZipCode.indexOf('-')).trim());
            }
            if(powerLimitation != null){
                int avaiableValue = powerLimitation.getPowerLimit()-powerLimitation.getUsedPower();
                if(avaiableValue < customer.getKwValue()){
                    logger.info("Dostępne jest tylko: " + avaiableValue+"kW");
                    return null;
                }else {
                    powerLimitation.setUsedPower(powerLimitation.getUsedPower()+customer.getKwValue());
                    try {
                        powerLimitationService.updatePowerLimitation(powerLimitation);
                    }catch (Exception e){
                        logger.info(e.getMessage());
                        return null;
                    }

                    customer = customerRepository.save(customer);
                    logger.info("Added customer");
                    return customer;
                }
            }else {
                powerLimitation = powerLimitationRepository.getByZipCode("default");
                if(powerLimitation.getPowerLimit() >= customer.getKwValue()){
                    customer = customerRepository.save(customer);
                    logger.info("Added customer");
                    return customer;
                }
            }
        }
        logger.info("Sprawdź dane, są niepoprawne!");
        return null;
    }
}
