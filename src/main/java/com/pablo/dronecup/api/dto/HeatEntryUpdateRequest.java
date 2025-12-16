package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class HeatEntryUpdateRequest {

    private Integer startPosicion;
    private Long pilotId;
    private Long HeatId;

    public HeatEntryUpdateRequest() {
    }

    public HeatEntryUpdateRequest(Integer startPosicion, Long pilotId, Long heatId) {
        this.startPosicion = startPosicion;
        this.pilotId = pilotId;
        HeatId = heatId;
    }

    public Integer getStartPosicion() {
        return startPosicion;
    }

    public void setStartPosicion(Integer startPosicion) {
        this.startPosicion = startPosicion;
    }

    public Long getPilotId() {
        return pilotId;
    }

    public void setPilotId(Long pilotId) {
        this.pilotId = pilotId;
    }

    public Long getHeatId() {
        return HeatId;
    }

    public void setHeatId(Long heatId) {
        HeatId = heatId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o ) return true;
        if (!(o instanceof HeatEntryUpdateRequest that)) return false;
        return Objects.equals(startPosicion, that.startPosicion) && Objects.equals(pilotId, that.pilotId) && Objects.equals(HeatId, that.HeatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPosicion, pilotId, HeatId);
    }

    @Override
    public String toString() {
        return "HeatEntryUpdateRequest{" +
                "startPosicion=" + startPosicion +
                ", pilotId=" + pilotId +
                ", HeatId=" + HeatId +
                '}';
    }
}
