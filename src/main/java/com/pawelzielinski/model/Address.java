package com.pawelzielinski.model;

import com.pawelzielinski.interfaces.SimpleValidation;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address implements SimpleValidation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Address(String country, String voivodeship, String city, String zipCode, String streetName, int houseNo, int apartmentNo) {
        this.country = country;
        this.voivodeship = voivodeship;
        this.city = city;
        this.zipCode = zipCode;
        this.streetName = streetName;
        this.houseNo = houseNo;
        this.apartmentNo = apartmentNo;
    }

    public Address(String country, String voivodeship, String city, String zipCode, String streetName, int houseNo) {
        this.country = country;
        this.voivodeship = voivodeship;
        this.city = city;
        this.zipCode = zipCode;
        this.streetName = streetName;
        this.houseNo = houseNo;
    }

    @Override
    public int checkIfAnyValueIsBlankNullOrEmpty() {

        if(country == null){
            return 1;
        }else if(country.isBlank()) {
            return 2;
        }else if(country.isEmpty()){
            return 3;
        }

        if(voivodeship == null){
            return 1;
        }else if(voivodeship.isBlank()) {
            return 2;
        }else if(voivodeship.isEmpty()){
            return 3;
        }

        if(city == null){
            return 1;
        }else if(city.isBlank()) {
            return 2;
        }else if(city.isEmpty()){
            return 3;
        }

        if(zipCode == null){
            return 1;
        }else if(zipCode.isBlank()) {
            return 2;
        }else if(zipCode.isEmpty()){
            return 3;
        }
        if(streetName == null){
            return 1;
        }else if(streetName.isBlank()) {
            return 2;
        }else if(streetName.isEmpty()){
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
