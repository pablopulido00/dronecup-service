package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class DroneResponse {

    private Long id;
    private  String model;
    private String manufacture;

    public DroneResponse() {
    }

    public DroneResponse(Long id, String model, String manufacture) {
        this.id = id;
        this.model = model;
        this.manufacture = manufacture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DroneResponse that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DroneResponse{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", manufacture='" + manufacture + '\'' +
                '}';
    }
}
