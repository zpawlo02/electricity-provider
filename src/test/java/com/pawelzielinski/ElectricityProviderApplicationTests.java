package com.pawelzielinski;

import com.pawelzielinski.controller.CustomerController;
import com.pawelzielinski.model.Address;
import com.pawelzielinski.model.Customer;
import com.pawelzielinski.model.PowerLimitation;
import com.pawelzielinski.repository.CustomerRepository;
import com.pawelzielinski.service.CustomerService;
import com.pawelzielinski.service.PowerLimitationService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@EnableJpaRepositories("com.pawelzielinski*")
@ComponentScan({"com.pawelzielinski*"})
class ElectricityProviderApplicationTests {


	@Autowired
	CustomerService customerService;

	@Autowired
	PowerLimitationService powerLimitationService;

	private static final Logger logger = LoggerFactory.getLogger(ElectricityProviderApplicationTests.class);

	@Test
	void contextLoads(){

	}

	@Before
	public void setUp(){

	}


	@Test
	void addNewPoweLimitationWithNullZipCode(){
		//GIVEN
		PowerLimitation powerLimitation = new PowerLimitation(2000, 0, null);
		//WHEN
		powerLimitation = powerLimitationService.addPowerLimitation(powerLimitation);
		//THAN
		assertNull(powerLimitation);
		logger.info("Nie można dodać limitu!");
	}

	@Test
	void addDefaultPowerLimitation(){
		//GIVEN
		PowerLimitation powerLimitationDefault = new PowerLimitation(1000, 0, "default");
		//WHEN
		powerLimitationDefault = powerLimitationService.addPowerLimitation(powerLimitationDefault);
		//THAN
		assertNotNull(powerLimitationDefault);
		logger.info("Dodano domyślny limit");
	}

	@Test
	void addNewCustomerWithDefaultPowerLimitation(){
		//GIVEN
		PowerLimitation powerLimitationDefault = new PowerLimitation(1000, 0, "default");
		Customer customer = new Customer("Marian", "Marianski", 1000,
				new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "Wodna", 10, 5), "");

		powerLimitationService.addPowerLimitation(powerLimitationDefault);

		//WHEN
		customer = customerService.addCustomer(customer);

		//THAN
		assertNotNull(customer);
	}

	@Test
	void addNewCustomerWithNullValueInCustomer(){
		//GIVEN
		PowerLimitation powerLimitationDefault = new PowerLimitation(1000, 0, "default");
		Customer customer = new Customer(null, "Marianski", 1000,
				new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "Wodna", 10, 5), "");

		powerLimitationService.addPowerLimitation(powerLimitationDefault);

		//WHEN
		customer = customerService.addCustomer(customer);

		//THAN
		assertNull(customer);
	}

	@Test
	void addNewCustomerWithNullValueInAddress(){
		//GIVEN

		Customer customer = new Customer("Jan", "Marianski", 1000,
				new Address("Polska", null, "Nałęczów", "20-894", "Wodna", 10, 5), "");

		//WHEN
		customer = customerService.addCustomer(customer);

		//THAN
		assertNull(customer);
	}

	@Test
	void addNewCustomerWithZipCodeGroup(){
		//GIVEN
		PowerLimitation powerLimitationGroup = new PowerLimitation(1000, 0, "30");
		Customer customer = new Customer("Marian", "Marianski", 1000,
				new Address("Polska", "Lubelskie", "Nałęczów", "30-894", "Wodna", 10, 5), "");

		powerLimitationGroup = powerLimitationService.addPowerLimitation(powerLimitationGroup);

		//WHEN
		customer = customerService.addCustomer(customer);

		//THAN
		assertNotNull(customer);
	}

	@Test
	void checkUsedValueAfterAddingCustomer(){
		//GIVEN
		PowerLimitation powerLimitation = new PowerLimitation(1000, 0, "30");
		Customer customer = new Customer("Marian", "Marianski", 1000,
				new Address("Polska", "Lubelskie", "Nałęczów", "30-894", "Wodna", 10, 5), "");

		//WHEN
		powerLimitationService.addPowerLimitation(powerLimitation);
		customerService.addCustomer(customer);

		powerLimitation = powerLimitationService.getPowerLimitationByZipCode("30");

		//THAN
		assertEquals(1000, powerLimitation.getUsedPower());

	}

	@Test
	void checkFilteringCustomerByFirstName(){

		//GIVEN
		Customer customer = new Customer("Marian", "Marianski", 1000,
				new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "Wodna", 10, 5), "");
		Customer customer1 = new Customer("Kornel", "Marianski", 1000,
				new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "Wodna", 10, 5), "");
		Customer customer2 = new Customer("Jan", "WWW", 100,
				new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "W", 10, 5), "");

		PowerLimitation powerLimitationDefault = new PowerLimitation(1000, 0, "default");

		powerLimitationService.addPowerLimitation(powerLimitationDefault);

		customerService.addCustomer(customer);
		customerService.addCustomer(customer1);
		customerService.addCustomer(customer2);

		//THAN
		List<Customer> customerList = customerService.getAllByFirstName("Marian");

		logger.info(customerList.toString());
		assertEquals(1, customerList.size());


	}

}

