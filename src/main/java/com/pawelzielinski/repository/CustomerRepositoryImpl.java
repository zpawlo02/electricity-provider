package com.pawelzielinski.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.pawelzielinski.model.Customer;
import com.pawelzielinski.model.QCustomer;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQuery;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom{

    @PersistenceContext
    private EntityManager em;
    private QCustomer qCustomer = QCustomer.customer;
    @Override
    public JPAQuery <Customer> findAllByFirsName(String firstName) {
        JPAQuery <Customer> jpaQuery = new JPAQuery<>(em);
        return jpaQuery.from(qCustomer).where(qCustomer.firstName.eq(firstName));
    }
    @Override
    public JPAQuery <Customer> findAllByKwValueEquals(int kwValue){
        JPAQuery <Customer> jpaQuery = new JPAQuery<>(em);
        return jpaQuery.from(qCustomer).where(qCustomer.kwValue.eq(kwValue));
    }

    @Override
    public JPAQuery <Customer> findAllByCountry(String country){
        JPAQuery <Customer> jpaQuery = new JPAQuery<>(em);
        return jpaQuery.from(qCustomer).where(qCustomer.address.country.eq(country));
    }

    public List<Customer> fetchData(JPAQuery<Customer> jpaQueryCustomer){
        return jpaQueryCustomer.fetch();
    }

    public JPAQuery <Customer> sortDesc(String objectName){
        JPAQuery <Customer> jpaQuery = new JPAQuery<>(em);
        switch (objectName){
            case "firstName":
                return jpaQuery.from(qCustomer).orderBy(qCustomer.firstName.desc());
            case "lastName":
                return jpaQuery.from(qCustomer).orderBy(qCustomer.lastName.desc());
            case "kwValue":
                return jpaQuery.from(qCustomer).orderBy(qCustomer.kwValue.desc());
        }
        return null;
    }

    public JPAQuery <Customer> sortAsc(String objectName){
        JPAQuery <Customer> jpaQuery = new JPAQuery<>(em);
        switch (objectName){
            case "firstName":
                return jpaQuery.from(qCustomer).orderBy(qCustomer.firstName.asc());
            case "lastName":
                return jpaQuery.from(qCustomer).orderBy(qCustomer.lastName.asc());
            case "kwValue":
                return jpaQuery.from(qCustomer).orderBy(qCustomer.kwValue.asc());
        }
        return null;
    }


    @Override
    public List<Tuple> findAllDuplicatesGroupByFirstNameAndLastName(){
        JPAQuery <Customer> jpaQuery = new JPAQuery<>(em);
        return jpaQuery.from(qCustomer)
                .select(qCustomer.firstName,qCustomer.lastName, qCustomer.firstName.count())
                .groupBy(qCustomer.firstName)
                .groupBy(qCustomer.lastName).fetch();

    }
}
