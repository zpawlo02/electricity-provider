package com.pawelzielinski.repository;

import com.pawelzielinski.model.Customer;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<Customer> findAllByFirsName(String firstName);
}
