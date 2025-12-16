package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class StandingCreateRequest {

    private Integer points;
    private Long championshipId;
    private Long pilotId;

    public StandingCreateRequest() {
    }

    public StandingCreateRequest(Integer points, Long championshipId, Long pilotId) {
        this.points = points;
        this.championshipId = championshipId;
        this.pilotId = pilotId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Long getChampionshipId() {
        return championshipId;
    }

    public void setChampionshipId(Long championshipId) {
        this.championshipId = championshipId;
    }

    public Long getPilotId() {
        return pilotId;
    }

    public void setPilotId(Long pilotId) {
        this.pilotId = pilotId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o ) return false;
        if (!(o instanceof StandingCreateRequest that)) return false;
        return Objects.equals(points, that.points) && Objects.equals(championshipId, that.championshipId) && Objects.equals(pilotId, that.pilotId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points, championshipId, pilotId);
    }

    @Override
    public String toString() {
        return "StandingCreateRequest{" +
                "points=" + points +
                ", championshipId=" + championshipId +
                ", pilotId=" + pilotId +
                '}';
    }


}
