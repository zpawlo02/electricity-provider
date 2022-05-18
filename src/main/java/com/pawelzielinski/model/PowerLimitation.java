package com.pawelzielinski.model;

import javax.persistence.*;

@Entity
@Table(name = "power_limitation")
public class PowerLimitation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int powerLimit;

    private int usedPower;

    private String zipCode;

    public PowerLimitation() {
    }

    public PowerLimitation(int powerLimit, int usedPower, String zipCode) {
        this.powerLimit = powerLimit;
        this.usedPower = usedPower;
        this.zipCode = zipCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPowerLimit() {
        return powerLimit;
    }

    public void setPowerLimit(int powerLimit) {
        this.powerLimit = powerLimit;
    }

    public int getUsedPower() {
        return usedPower;
    }

    public void setUsedPower(int usedPower) {
        this.usedPower = usedPower;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
