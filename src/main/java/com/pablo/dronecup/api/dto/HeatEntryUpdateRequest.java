package com.pablo.dronecup.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.Objects;

public class HeatEntryUpdateRequest {

    @Min(1)
    @Max(10)
    private Integer startPosicion;

    public HeatEntryUpdateRequest() {
    }

    public HeatEntryUpdateRequest(Integer startPosicion) {
        this.startPosicion = startPosicion;
    }

    public Integer getStartPosicion() {
        return startPosicion;
    }

    public void setStartPosicion(Integer startPosicion) {
        this.startPosicion = startPosicion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeatEntryUpdateRequest that)) return false;
        return Objects.equals(startPosicion, that.startPosicion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPosicion);
    }

    @Override
    public String toString() {
        return "HeatEntryUpdateRequest{" +
                "startPosicion=" + startPosicion +
                '}';
    }
}
