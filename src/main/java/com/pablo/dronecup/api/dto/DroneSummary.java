package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class DroneSummary {

    private Long id;
    private String model;

    public DroneSummary(){}

    public DroneSummary(Long id, String model) {
        this.id = id;
        this.model = model;
    }


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getModel (){
        return model;
    }

    public void setModel(String model){
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DroneSummary droneSummary)) return false;
        return Objects.equals(id, droneSummary.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DroneSummary{" +
                "id=" + id +
                ", model='" + model + '\'' +
                '}';
    }
}
