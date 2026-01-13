package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class DroneUpdateRequest {


    private String model;
    private String manufacturer;

    public DroneUpdateRequest() {
    }

    public DroneUpdateRequest(String model, String manufacturer) {

        this.model = model;
        this.manufacturer = manufacturer;
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
        return  Objects.equals(model, that.model) && Objects.equals(manufacturer, that.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash( model, manufacturer);
    }

    @Override
    public String toString() {
        return "DroneUpdateRequest{" +
                " model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
