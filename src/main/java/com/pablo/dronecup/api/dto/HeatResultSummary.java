package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class HeatResultSummary {

    private Integer position;
    private Double bestLapTime;
    private Double totalTime;
    private Integer penalties;

    public HeatResultSummary() {
    }

    public HeatResultSummary(Integer position, Double bestLapTime, Double totalTime, Integer penalties) {
        this.position = position;
        this.bestLapTime = bestLapTime;
        this.totalTime = totalTime;
        this.penalties = penalties;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof HeatResultSummary that)) return false;
        return Objects.equals(position, that.position) && Objects.equals(bestLapTime, that.bestLapTime) && Objects.equals(totalTime, that.totalTime) && Objects.equals(penalties, that.penalties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, bestLapTime, totalTime, penalties);
    }

    @Override
    public String toString() {
        return "HeatResultSummary{" +
                "position=" + position +
                ", bestLapTime=" + bestLapTime +
                ", totalTime=" + totalTime +
                ", penalties=" + penalties +
                '}';
    }
}
