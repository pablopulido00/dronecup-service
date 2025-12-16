package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class DroneCreateRequest {

    private String model;
    private String manufacturer;

    public DroneCreateRequest() {
    }

    public DroneCreateRequest(String model, String manufacturer) {
        this.model = model;
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DroneCreateRequest that)) return false;
        return Objects.equals(model, that.model) && Objects.equals(manufacturer, that.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, manufacturer);
    }

    @Override
    public String toString() {
        return "DroneCreateRequest{" +
                "model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
