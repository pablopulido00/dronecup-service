package com.pablo.dronecup.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Objects;

public class HeatResultUpdateRequest {

    @Min(1)
    @Max(10)
    private Integer position;

    @Positive
    private Double bestLapTime;

    @Positive
    private Double totalTime;

    @PositiveOrZero
    private Integer penalties;

    public HeatResultUpdateRequest() {
    }

    public HeatResultUpdateRequest(
            Integer position,
            Double bestLapTime,
            Double totalTime,
            Integer penalties
    ) {
        this.position = position;
        this.bestLapTime = bestLapTime;
        this.totalTime = totalTime;
        this.penalties = penalties;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeatResultUpdateRequest that)) return false;
        return Objects.equals(position, that.position)
                && Objects.equals(bestLapTime, that.bestLapTime)
                && Objects.equals(totalTime, that.totalTime)
                && Objects.equals(penalties, that.penalties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, bestLapTime, totalTime, penalties);
    }

    @Override
    public String toString() {
        return "HeatResultUpdateRequest{" +
                "position=" + position +
                ", bestLapTime=" + bestLapTime +
                ", totalTime=" + totalTime +
                ", penalties=" + penalties +
                '}';
    }
}
