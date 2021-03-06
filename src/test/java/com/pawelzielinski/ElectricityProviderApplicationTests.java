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
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@EnableJpaRepositories("com.pawelzielinski*")
@ComponentScan({"com.pawelzielinski*"})
class ElectricityProviderApplicationTests {

	@Test
	void contextLoads(){

	}

	@Before
	public void setUp(){

	}

}

