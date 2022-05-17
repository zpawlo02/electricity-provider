package com.pawelzielinski.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String firstName, lastName;
    private int kwValue;

    private Address adress;

    @Basic
    private String note;

    public Customer() {
    }

    public Customer(int id, String firstName, String lastName, int kwValue, Address adress, String note) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.kwValue = kwValue;
        this.adress = adress;
        this.note = note;
    }

    public Customer(int id, String firstName, String lastName, int kwValue, Address adress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.kwValue = kwValue;
        this.adress = adress;
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

    public Address getAdress() {
        return adress;
    }

    public void setAdress(Address adress) {
        this.adress = adress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
