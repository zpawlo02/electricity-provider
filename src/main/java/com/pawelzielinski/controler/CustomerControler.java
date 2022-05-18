package com.pawelzielinski.controler;

import com.pawelzielinski.model.Customer;
import com.pawelzielinski.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer, @PathVariable Integer id){

        customer.setId(customerRepository.getById(id).getId());
        customerRepository.save(customer);
        return ResponseEntity.ok("Customer updated");
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable Integer id){
        Customer customer = customerRepository.getById(id);
        String txt = "Customer "+ customer.getFirstName() + " " + customer.getLastName() + " deleted!";
        customerRepository.deleteById(id);
        return ResponseEntity.ok(txt);
    }



}
