package com.pawelzielinski;

import com.pawelzielinski.controller.CustomerController;
import com.pawelzielinski.model.Address;
import com.pawelzielinski.model.Customer;
import com.pawelzielinski.model.PowerLimitation;
import com.pawelzielinski.repository.CustomerRepository;
import com.pawelzielinski.service.CustomerService;
import com.pawelzielinski.service.PowerLimitationService;
import org.junit.Before;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@EnableJpaRepositories("com.pawelzielinski*")
@ComponentScan({"com.pawelzielinski*"})
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    PowerLimitationService powerLimitationService;

    private static final Logger logger = LoggerFactory.getLogger(ElectricityProviderApplicationTests.class);


    @Test
    public void addNewCustomerWithDefaultPowerLimitation(){
        //GIVEN
        PowerLimitation powerLimitationDefault = new PowerLimitation(1000, 0, "default");
        Customer customer = new Customer("Marian", "Marianski", 1000,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "Wodna", 10, 5), "");

        powerLimitationService.addPowerLimitation(powerLimitationDefault);

        //WHEN
        customer = customerService.addCustomer(customer);

        //THEN
        assertNotNull(customer);
    }

    @Test
    public void addNewCustomerWithNullValueInCustomer(){
        //GIVEN
        PowerLimitation powerLimitationDefault = new PowerLimitation(1000, 0, "default");
        Customer customer = new Customer(null, "Marianski", 1000,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "Wodna", 10, 5), "");

        powerLimitationService.addPowerLimitation(powerLimitationDefault);

        //WHEN
        customer = customerService.addCustomer(customer);

        //THEN
        assertNull(customer);
    }

    @Test
    public void addNewCustomerWithNullValueInAddress(){
        //GIVEN

        Customer customer = new Customer("Jan", "Marianski", 1000,
                new Address("Polska", null, "Nałęczów", "20-894", "Wodna", 10, 5), "");

        //WHEN
        customer = customerService.addCustomer(customer);

        //THEN
        assertNull(customer);
    }

    @Test
    public void addNewCustomerWithZipCodeGroup(){
        //GIVEN
        PowerLimitation powerLimitationGroup = new PowerLimitation(1000, 0, "30");
        Customer customer = new Customer("Marian", "Marianski", 1000,
                new Address("Polska", "Lubelskie", "Nałęczów", "30-894", "Wodna", 10, 5), "");

        powerLimitationGroup = powerLimitationService.addPowerLimitation(powerLimitationGroup);

        //WHEN
        customer = customerService.addCustomer(customer);

        //THEN
        assertNotNull(customer);
    }

    @Test
    public void checkUsedValueAfterAddingCustomer(){
        //GIVEN
        PowerLimitation powerLimitation = new PowerLimitation(1000, 0, "30");
        Customer customer = new Customer("Marian", "Marianski", 1000,
                new Address("Polska", "Lubelskie", "Nałęczów", "30-894", "Wodna", 10, 5), "");

        //WHEN
        powerLimitationService.addPowerLimitation(powerLimitation);
        customerService.addCustomer(customer);

        powerLimitation = powerLimitationService.getPowerLimitationByZipCode("30");

        //THEN
        assertEquals(1000, powerLimitation.getUsedPower());

    }

    @Test
    public void checkFilteringCustomerByFirstName(){

        //GIVEN
        Customer customer = new Customer("Marian", "Marianski", 1000,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "Wodna", 10, 5), "");
        Customer customer1 = new Customer("Kornel", "Marianski", 1000,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "Wodna", 10, 5), "");
        Customer customer2 = new Customer("Jan", "WWW", 100,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "W", 10, 5), "");

        PowerLimitation powerLimitationDefault = new PowerLimitation(1000, 0, "default");

        //WHEN

        powerLimitationService.addPowerLimitation(powerLimitationDefault);

        customerService.addCustomer(customer);
        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);

        //THEN
        List<Customer> customerList = customerService.getAllByFirstName("Jan");

        logger.info(customerList.toString());
        assertEquals(1, customerList.size());
        assertEquals("Jan", customerList.get(0).getFirstName());
    }

    @Test
    public void checkFilteringCustomerByKwValueEquals(){

        //GIVEN
        Customer customer = new Customer("Marian", "Marianski", 1000,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "Wodna", 10, 5), "");
        Customer customer1 = new Customer("Kornel", "Marianski", 300,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "Wodna", 10, 5), "");
        Customer customer2 = new Customer("Jan", "WWW", 100,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "W", 10, 5), "");

        PowerLimitation powerLimitationDefault = new PowerLimitation(1000, 0, "default");

        //WHEN
        powerLimitationService.addPowerLimitation(powerLimitationDefault);

        customerService.addCustomer(customer);
        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);

        //THEN
        List<Customer> customerList = customerService.getAllByKwValueEquals(300);

        logger.info(customerList.toString());
        assertEquals(1, customerList.size());
        assertEquals(300, customerList.get(0).getKwValue());
    }

    @Test
    public void checkFilteringCustomerByCountry(){

        //GIVEN
        Customer customer = new Customer("Marian", "Marianski", 1000,
                new Address("Niemct", "Lubelskie", "Nałęczów", "20-894", "Wodna", 10, 5), "");
        Customer customer1 = new Customer("Kornel", "Marianski", 300,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "Wodna", 10, 5), "");
        Customer customer2 = new Customer("Jan", "WWW", 100,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "W", 10, 5), "");

        PowerLimitation powerLimitationDefault = new PowerLimitation(1000, 0, "default");

        //WHEN
        powerLimitationService.addPowerLimitation(powerLimitationDefault);

        customerService.addCustomer(customer);
        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);

        //THEN
        List<Customer> customerList = customerService.getAllByCountry("Polska");

        logger.info(customerList.toString());
        assertEquals(2, customerList.size());
        assertEquals("Polska", customerList.get(0).getAddress().getCountry());
    }


    @Test
    public void checkSortingDesc(){
        //GIVEN
        Customer customer = new Customer("Aarian", "Marianski", 1000,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "Wodna", 10, 5), "");
        Customer customer1 = new Customer("Bornel", "Marianski", 300,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "Wodna", 10, 5), "");
        Customer customer2 = new Customer("Can", "WWW", 100,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "W", 10, 5), "");

        PowerLimitation powerLimitationDefault = new PowerLimitation(1000, 0, "default");

        powerLimitationService.addPowerLimitation(powerLimitationDefault);

        customerService.addCustomer(customer);
        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);

        //THEN
        List<Customer> customerList = customerService.getAllSortedDesc("firstName");

        logger.info(customerList.toString());
        assertEquals(3, customerList.size());
        assertEquals("Can", customerList.get(0).getFirstName());
    }

    @Test
    public void checkSortingAsc(){
        //GIVEN
        Customer customer = new Customer("Aarian", "Marianski", 1000,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "Wodna", 10, 5), "");
        Customer customer1 = new Customer("Bornel", "Marianski", 300,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "Wodna", 10, 5), "");
        Customer customer2 = new Customer("Can", "WWW", 100,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "W", 10, 5), "");

        PowerLimitation powerLimitationDefault = new PowerLimitation(1000, 0, "default");

        powerLimitationService.addPowerLimitation(powerLimitationDefault);

        customerService.addCustomer(customer);
        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);

        //THEN
        List<Customer> customerList = customerService.getAllSortedAsc("firstName");

        logger.info(customerList.toString());
        assertEquals(3, customerList.size());
        assertEquals("Aarian", customerList.get(0).getFirstName());
    }
    @Test
    public void getDuplicatesOfUsers(){

        //GIVEN
        Customer customer = new Customer("Marian", "Marianski", 1000,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "Wodna", 10, 5), "");
        Customer customer1 = new Customer("Marian", "Marianski", 1000,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "Wodna", 10, 5), "");
        Customer customer2 = new Customer("Jan", "WWW", 100,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "W", 10, 5), "");

        Customer customer3 = new Customer("Jan", "WWW", 100,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "W", 10, 5), "");

        Customer customer4 = new Customer("Jan", "WWW", 100,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "W", 10, 5), "");

        Customer customer5 = new Customer("Kacper", "Kacperowski", 100,
                new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "W", 10, 5), "");

        PowerLimitation powerLimitationDefault = new PowerLimitation(1000, 0, "default");

        //WHEN
        powerLimitationService.addPowerLimitation(powerLimitationDefault);

        customerService.addCustomer(customer);
        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);
        customerService.addCustomer(customer3);
        customerService.addCustomer(customer4);
        customerService.addCustomer(customer5);

        //THEN
        Map<Object, Object> mapOfCustomers = customerService.getCustomerNameAndServicesCount();
        logger.info(mapOfCustomers.toString());
        assertEquals(2, mapOfCustomers.size());;
        assertEquals(3L, mapOfCustomers.get("Jan WWW"));

    }
}
