package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class StandingSummary {

    private Long id;
    private Integer points;

    public StandingSummary() {
    }

    public StandingSummary(Long id, Integer points) {
        this.id = id;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StandingSummary that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String  toString() {
        return "StandingSummary{" +
                "id=" + id +
                ", points=" + points +
                '}';
    }
}
