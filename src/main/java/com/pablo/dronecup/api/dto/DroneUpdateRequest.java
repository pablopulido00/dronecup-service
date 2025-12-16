package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class DroneUpdateRequest {

    private String name;
    private String model;
    private String manufacturer;

    public DroneUpdateRequest() {
    }

    public DroneUpdateRequest(String name, String model, String manufacturer) {
        this.name = name;
        this.model = model;
        this.manufacturer = manufacturer;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DroneUpdateRequest that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(model, that.model) && Objects.equals(manufacturer, that.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, model, manufacturer);
    }

    @Override
    public String toString() {
        return "DroneUpdateRequest{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
