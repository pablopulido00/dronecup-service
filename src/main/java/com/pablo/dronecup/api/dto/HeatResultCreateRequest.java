package com.pablo.dronecup.api.dto;

import jakarta.validation.constraints.*;

import java.util.Objects;

public class HeatResultCreateRequest {

    @NotNull
    @Min(1)
    @Max(10)
    private Integer position;

    @NotNull
    @PositiveOrZero
    private Double bestLapTime;

    @NotNull
    @PositiveOrZero
    private Double totalTime;

    @NotNull
    @PositiveOrZero
    private Integer penalties;

    @NotNull
    @Positive
    private Long heatEntryId;

    public HeatResultCreateRequest() {
    }

    public HeatResultCreateRequest(Integer position, Double bestLapTime, Double totalTime, Integer penalties, Long heatEntryId) {
        this.position = position;
        this.bestLapTime = bestLapTime;
        this.totalTime = totalTime;
        this.penalties = penalties;
        this.heatEntryId = heatEntryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeatResultCreateRequest that)) return false;
        return Objects.equals(position, that.position) && Objects.equals(bestLapTime, that.bestLapTime) && Objects.equals(totalTime, that.totalTime) && Objects.equals(penalties, that.penalties) && Objects.equals(heatEntryId, that.heatEntryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, bestLapTime, totalTime, penalties, heatEntryId);
    }

    @Override
    public String toString() {
        return "HeatResultCreateRequest{" +
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
