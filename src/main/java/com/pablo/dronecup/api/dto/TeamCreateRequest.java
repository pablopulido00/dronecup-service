package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class TeamCreateRequest {


    private String name;
    private String country;

    public TeamCreateRequest(){}

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
    public boolean equals(Object o) {
        if(this == o) return true;
        if (!(o instanceof TeamCreateRequest that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }

    @Override
    public String toString() {
        return "TeamCreateRequest{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
