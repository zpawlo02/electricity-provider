package com.pawelzielinski.service;

import com.google.common.collect.Maps;
import com.pawelzielinski.model.Customer;
import com.pawelzielinski.model.PowerLimitation;
import com.pawelzielinski.repository.CustomerRepository;
import com.pawelzielinski.repository.CustomerRepositoryImpl;
import com.pawelzielinski.repository.PowerLimitationRepository;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return customerRepositoryImpl.findAllByFirsName(firstName).fetch();
    }

    public List<Customer> getAllByKwValueEquals(int kwValue){
        return customerRepositoryImpl.findAllByKwValueEquals(kwValue).fetch();
    }

    public List<Customer> getAllByCountry(String country){
        return customerRepositoryImpl.findAllByCountry(country).fetch();
    }

    public List<Customer> filterBy(String keyword, String value){
        switch (keyword){
            case "firstName":
                return getAllByFirstName(value);
            case "Country":
                return getAllByCountry(value);
            case "kvValue":
                return getAllByKwValueEquals(Integer.parseInt(value));
        }
        return null;
    }

    //objectName is a name of variable which can be used to sort
    // firstName, lastName, kwValue
    public List<Customer> getAllSortedDesc(String objectName){
        return customerRepositoryImpl.sortDesc(objectName).fetch();
    }

    public List<Customer> getAllSortedAsc(String objectName){
        return customerRepositoryImpl.sortAsc(objectName).fetch();
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

    //we are getting from List<tuple> three values: firstName, lastName and count of duplicates
    //which means how many services got one customer and next we are getting map from it
    //stream is filtered if count value is not greater than 1 value is not added to map
    public Map<Object, Object> getCustomerNameAndServicesCount(){
        return customerRepositoryImpl
                .findAllDuplicatesGroupByFirstNameAndLastName()
                .stream()
                .filter(t -> t.get(2, Long.class) > 1L).
                collect(
                        Collectors.toMap(
                                t ->  t.get(0, Object.class) + " " + t.get(1, Object.class),
                                t -> t.get(2, Object.class)
                        ));

    }
}
