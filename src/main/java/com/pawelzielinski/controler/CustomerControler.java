package com.pawelzielinski.controler;

import com.pawelzielinski.model.Customer;
import com.pawelzielinski.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerControler {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/addCustomer")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer){
        if(customer.checkIfAnyValueIsBlankNullOrEmpty() == 0 && customer.getAddress().checkIfAnyValueIsBlankNullOrEmpty() == 0){
            customerRepository.save(customer);
            return ResponseEntity.ok("Added customer");
        }

        return ResponseEntity.ok("Sprawdź dane, są niepoprawne!");
    }

    @GetMapping("/customers")
    public ResponseEntity<?> readAllCustomers(){
        return ResponseEntity.ok(customerRepository.findAll());
    }

}
