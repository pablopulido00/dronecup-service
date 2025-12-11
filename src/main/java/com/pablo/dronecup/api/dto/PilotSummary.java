package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class PilotSummary {

    private Long id;
    private String name;

    public PilotSummary(){

    }

    public PilotSummary(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PilotSummary that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PilotSummary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
