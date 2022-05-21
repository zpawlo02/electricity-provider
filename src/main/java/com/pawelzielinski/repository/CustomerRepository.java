package com.pawelzielinski.repository;

import com.pawelzielinski.model.Customer;
import com.pawelzielinski.specifications.CustomerSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer save(Customer customer);
    List<Customer> findAll();
    Customer getById(Integer id);
    void deleteById(Integer id);

}
