package com.pawelzielinski.repository;

import com.pawelzielinski.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomerRepository {

    Customer save(Customer customer);
    List<Customer> findAll();
    Customer getById(Integer id);
    void deleteById(Integer id);


}
