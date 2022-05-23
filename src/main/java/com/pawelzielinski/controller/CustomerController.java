package com.pawelzielinski.controller;

import com.pawelzielinski.model.Customer;
import com.pawelzielinski.model.PowerLimitation;
import com.pawelzielinski.repository.CustomerRepository;
import com.pawelzielinski.repository.PowerLimitationRepository;
import com.pawelzielinski.service.CustomerService;
import com.pawelzielinski.service.PowerLimitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PowerLimitationRepository powerLimitationRepository;

    @Autowired
    private PowerLimitationService powerLimitationService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/addCustomer")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer){
        if(customer.checkIfAnyValueIsBlankNullOrEmpty() == 0 && customer.getAddress().checkIfAnyValueIsBlankNullOrEmpty() == 0){
            customer = customerService.addCustomer(customer);
            if(customer != null){
                return ResponseEntity.ok("Użytkownik został dodany!");
            }
        }

        return ResponseEntity.ok("Sprawdź dane, są niepoprawne!");
    }

    @GetMapping("/customers")
    public ResponseEntity<?> readAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer, @PathVariable Integer id){
        customer =  customerService.updateCustomer(id, customer);
        if(customer != null){
            return ResponseEntity.ok("Użytkownik zaaktulizowany!");
        }
        return ResponseEntity.ok("Użytkownik nie został zaaktualizowany!");
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Użytkownik usunięty!");
    }

    @GetMapping("/customer/sortByDesc")
    public ResponseEntity<String> sortByDesc(@PathVariable String keyword){
        return ResponseEntity.ok(customerService.getAllSortedDesc(keyword).toString());
    }

    @GetMapping("/customer/sortByAsc")
    public ResponseEntity<String> sortByAsc(@PathVariable String keyword){
        return ResponseEntity.ok(customerService.getAllSortedAsc(keyword).toString());
    }
    @GetMapping("/customer/filterBy")
    public ResponseEntity<String> filterBy(@PathVariable String keyword, String value){
        return ResponseEntity.ok(customerService.filterBy(keyword, value).toString());
    }

    @GetMapping("/customer/duplicates")
    public ResponseEntity<String> getDuplicates(){
        return ResponseEntity.ok(customerService.getCustomerNameAndServicesCount().toString());
    }


}
