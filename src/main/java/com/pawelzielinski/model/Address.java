package com.pawelzielinski.model;

import com.pawelzielinski.interfaces.SimpleValidation;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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

}
