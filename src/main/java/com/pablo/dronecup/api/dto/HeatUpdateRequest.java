package com.pablo.dronecup.api.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class HeatUpdateRequest {

    @Size(min = 1, max = 100)
    private String name;

    @Positive
    private Integer number;

    public HeatUpdateRequest() {
    }

    public HeatUpdateRequest(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeatUpdateRequest that)) return false;
        return Objects.equals(name, that.name)
                && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number);
    }

    @Override
    public String toString() {
        return "HeatUpdateRequest{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
