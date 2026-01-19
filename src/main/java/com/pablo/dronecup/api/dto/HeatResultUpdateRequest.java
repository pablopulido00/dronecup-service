package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class HeatResultUpdateRequest {

    private Integer position;
    private Double bestLapTime;
    private Double totalTime;
    private Integer penalties;
    private Long heatEntryId;

    public HeatResultUpdateRequest() {
    }

    public HeatResultUpdateRequest(Integer position, Double bestLapTime, Double totalTime, Integer penalties, Long heatEntryId) {
        this.position = position;
        this.bestLapTime = bestLapTime;
        this.totalTime = totalTime;
        this.penalties = penalties;
        this.heatEntryId = heatEntryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeatResultUpdateRequest that)) return false;
        return Objects.equals(position, that.position) && Objects.equals(bestLapTime, that.bestLapTime) && Objects.equals(totalTime, that.totalTime) && Objects.equals(penalties, that.penalties) && Objects.equals(heatEntryId, that.heatEntryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, bestLapTime, totalTime, penalties, heatEntryId);
    }

    @Override
    public String toString() {
        return "HeatResultUpdateRequest{" +
                "position=" + position +
                ", bestLapTime=" + bestLapTime +
                ", totalTime=" + totalTime +
                ", penalties=" + penalties +
                ", heatEntryId=" + heatEntryId +
                '}';
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
}
