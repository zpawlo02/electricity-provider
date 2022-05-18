package com.pawelzielinski;

import com.pawelzielinski.controller.CustomerController;
import com.pawelzielinski.model.Address;
import com.pawelzielinski.model.Customer;
import com.pawelzielinski.model.PowerLimitation;
import com.pawelzielinski.repository.CustomerRepository;
import com.pawelzielinski.service.CustomerService;
import com.pawelzielinski.service.PowerLimitationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElectricityProviderApplicationTests {


	@Autowired
	CustomerService customerService;

	@Autowired
	PowerLimitationService powerLimitationService;

	@Test
	void contextLoads() {
	}

	@Test
	void addNewCustomer(){

		PowerLimitation powerLimitationDefault = new PowerLimitation(1000, 0, "default");

		powerLimitationService.addPowerLimitation(powerLimitationDefault);

		Customer customer = new Customer("Marian", "Marianski", 1000,
				new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "Wodna", 10, 5), "");

		customerService.addCustomer(customer);
	}
}


