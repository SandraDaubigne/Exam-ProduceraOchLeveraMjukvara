package com.example.tennerr;

import javax.persistence.Entity;

@Entity
public class Workgiver extends User{

    String address;

    public Workgiver() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
