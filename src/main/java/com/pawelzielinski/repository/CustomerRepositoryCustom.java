package com.pawelzielinski.repository;

import com.pawelzielinski.model.Customer;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;

import java.util.List;

public interface CustomerRepositoryCustom {
    JPAQuery <Customer> findAllByFirsName(String firstName);
    JPAQuery <Customer> findAllByKwValueEquals(int kwValue);
    JPAQuery <Customer> findAllByCountry(String country);

    List<Tuple> findAllDuplicatesGroupByFirstNameAndLastName();
}
