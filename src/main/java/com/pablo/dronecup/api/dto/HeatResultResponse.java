package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class HeatResultResponse {

    private Long id;
    private Integer position;
    private Double bestLapTime;
    private Double totalTime;
    private Integer penalties;
    private Long heatEntryId;

    public HeatResultResponse() {
    }

    public HeatResultResponse(Long id, Integer position, Double bestLapTime, Double totalTime, Integer penalties, Long heatEntryId) {
        this.id = id;
        this.position = position;
        this.bestLapTime = bestLapTime;
        this.totalTime = totalTime;
        this.penalties = penalties;
        this.heatEntryId = heatEntryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Double getBestLapTime() {
        return bestLapTime;
    }

    public void setBestLapTime(Double bestLapTime) {
        this.bestLapTime = bestLapTime;
    }

    public Double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getPenalties() {
        return penalties;
    }

    public void setPenalties(Integer penalties) {
        this.penalties = penalties;
    }

    public Long getHeatEntryId() {
        return heatEntryId;
    }

    public void setHeatEntryId(Long heatEntryId) {
        this.heatEntryId = heatEntryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeatResultResponse that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String  toString() {
        return "HeatResultResponse{" +
                "id=" + id +
                ", position=" + position +
                ", bestLapTime=" + bestLapTime +
                ", totalTime=" + totalTime +
                ", penalties=" + penalties +
                ", heatEntryId=" + heatEntryId +
                '}';
    }
}
