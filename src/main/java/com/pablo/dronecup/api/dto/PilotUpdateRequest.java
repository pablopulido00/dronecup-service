package com.pablo.dronecup.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class PilotUpdateRequest {

    @Size(min = 1, max = 100)
    private String name;

    @Size(min = 1, max = 50)
    private String nationality;

    @Min(1)
    private Integer age;

    public PilotUpdateRequest() {
    }

    public PilotUpdateRequest(String name, String nationality, Integer age) {
        this.name = name;
        this.nationality = nationality;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PilotUpdateRequest that)) return false;
        return Objects.equals(name, that.name)
                && Objects.equals(nationality, that.nationality)
                && Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nationality, age);
    }

    @Override
    public String toString() {
        return "PilotUpdateRequest{" +
                "name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", age=" + age +
                '}';
    }
}
