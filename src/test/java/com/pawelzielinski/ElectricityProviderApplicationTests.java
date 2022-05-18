package com.pawelzielinski;

import com.pawelzielinski.model.Address;
import com.pawelzielinski.model.Customer;
import com.pawelzielinski.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElectricityProviderApplicationTests {

	@Autowired
	CustomerRepository customerRepository;


	@Test
	void contextLoads() {
	}

	@Test
	void addNewCustomer(){
		Customer customer = new Customer("Marian", "Marianski", 1000,
				new Address("Polska", "Lubelskie", "Nałęczów", "20-894", "Wodna", 10, 5), "");

		customerRepository.save(customer);
	}
}


