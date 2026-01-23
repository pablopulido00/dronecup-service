package com.pablo.dronecup.api.dto;


import jakarta.validation.constraints.Size;

import java.util.Objects;

public class TeamUpdateRequest {


    @Size(max = 100)
    private String name;


    @Size(max = 50)
    private String country;

    public TeamUpdateRequest() {
    }

    public TeamUpdateRequest(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals (Object o){
        if (this == o) return true;
        if (!(o instanceof TeamUpdateRequest otro)) return false;
        return Objects.equals(name, otro.name) && Objects.equals(country, otro.country);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, country);
    }

    @Override
    public String toString() {
        return "TeamUpdateRequest{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
