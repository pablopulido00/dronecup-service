package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class HeatResultCreateRequest {

    private Integer position;
    private Double bestLapTime;
    private Double totalTime;
    private Integer penalties;
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
}
