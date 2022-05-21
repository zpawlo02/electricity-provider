package com.pawelzielinski.repository;

import java.util.List;
import com.pawelzielinski.model.Customer;
import com.pawelzielinski.model.QCustomer;
import com.querydsl.jpa.impl.JPAQuery;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom{

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Customer> findAllByFirsName(String firstName) {

        JPAQuery <Customer> jpaQuery = new JPAQuery<>(em);
        QCustomer qCustomer = QCustomer.customer;
        List<Customer> lCustomers = jpaQuery.from(qCustomer).where(qCustomer.firstName.eq(firstName)).fetch();

        return lCustomers;

    }
}
