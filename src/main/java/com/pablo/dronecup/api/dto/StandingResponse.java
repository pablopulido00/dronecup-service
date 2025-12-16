package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class StandingResponse {

    private Long id;
    private Integer points;
    private ChampionshipSummary championship;
    private PilotSummary pilot;

    public StandingResponse() {
    }

    public StandingResponse(Long id, Integer points, ChampionshipSummary championship, PilotSummary pilot) {
        this.id = id;
        this.points = points;
        this.championship = championship;
        this.pilot = pilot;
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

    public ChampionshipSummary getChampionship() {
        return championship;
    }

    public void setChampionship(ChampionshipSummary championship) {
        this.championship = championship;
    }

    public PilotSummary getPilot() {
        return pilot;
    }

    public void setPilot(PilotSummary pilot) {
        this.pilot = pilot;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (!(o instanceof StandingResponse that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
