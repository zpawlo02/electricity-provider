package com.pawelzielinski.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pawelzielinski.interfaces.SimpleValidation;
import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer")
public class Customer implements SimpleValidation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private int kwValue;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @NonNull
    private Address address;

    @Basic
    @NonNull
    private String note;

    public Customer() {
        firstName = "";
        lastName = "";
        address = new Address();
        note = "";
    }

    public Customer(String firstName, String lastName, int kwValue, Address address, String note) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.kwValue = kwValue;
        this.address = address;
        this.note = note;
    }

    public Customer(String firstName, String lastName, int kwValue, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.kwValue = kwValue;
        this.address = address;
        note = "";
    }

    @Override
    public int checkIfAnyValueIsBlankNullOrEmpty(){

        if(firstName == null){
            return 1;
        }else if(firstName.isBlank()) {
            return 2;
        }else if(firstName.isEmpty()){
            return 3;
        }

        if(lastName == null){
            return 1;
        }else if(lastName.isBlank()) {
            return 2;
        }else if(lastName.isEmpty()){
            return 3;
        }


        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getKwValue() {
        return kwValue;
    }

    public void setKwValue(int kwValue) {
        this.kwValue = kwValue;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
