package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class HeatEntryResponse {

    private Long id;
    private Integer startPosicion;
    private Long pilotId;
    private Long heatId;
    private HeatResultSummary heatResult;

    public HeatEntryResponse() {
    }

    public HeatEntryResponse(Long id, Integer startPosicion, Long pilotId, Long heatId, HeatResultSummary heatResult) {
        this.id = id;
        this.startPosicion = startPosicion;
        this.pilotId = pilotId;
        this.heatId = heatId;
        this.heatResult = heatResult;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return heatId;
    }

    public void setHeatId(Long heatId) {
        this.heatId = heatId;
    }

    public HeatResultSummary getHeatResult() {
        return heatResult;
    }

    public void setHeatResult(HeatResultSummary heatResult) {
        this.heatResult = heatResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o ) return true;
        if (!(o instanceof HeatEntryResponse that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "HeatEntryResponse{" +
                "id=" + id +
                ", startPosicion=" + startPosicion +
                ", pilotId=" + pilotId +
                ", heatId=" + heatId +
                ", heatResult=" + heatResult +
                '}';
    }
}
