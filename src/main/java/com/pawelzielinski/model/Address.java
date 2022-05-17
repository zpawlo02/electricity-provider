package com.pawelzielinski.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private int id;

    private String country;

    private String voivodeship;

    private String city;

    private String zipCode;

    private String streetName;

    private int houseNo;

    @Basic
    private int apartmentNo;

    public Address() {
    }

    public Address(int id, String country, String voivodeship, String city, String zipCode, String streetName, int houseNo, int apartmentNo) {
        this.id = id;
        this.country = country;
        this.voivodeship = voivodeship;
        this.city = city;
        this.zipCode = zipCode;
        this.streetName = streetName;
        this.houseNo = houseNo;
        this.apartmentNo = apartmentNo;
    }

    public Address(int id, String country, String voivodeship, String city, String zipCode, String streetName, int houseNo) {
        this.id = id;
        this.country = country;
        this.voivodeship = voivodeship;
        this.city = city;
        this.zipCode = zipCode;
        this.streetName = streetName;
        this.houseNo = houseNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
    }

    public int getApartmentNo() {
        return apartmentNo;
    }

    public void setApartmentNo(int apartmentNo) {
        this.apartmentNo = apartmentNo;
    }
}
