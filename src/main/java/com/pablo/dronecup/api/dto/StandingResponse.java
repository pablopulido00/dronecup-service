package com.pablo.dronecup.api.dto;

public class StandingResponse {

    private Long id;
    private Long pilotId;
    private String pilotName;
    private Integer points;

    public StandingResponse(Long id, Long pilotId, String pilotName, Integer points) {
        this.id = id;
        this.pilotId = pilotId;
        this.pilotName = pilotName;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public Long getPilotId() {
        return pilotId;
    }

    public String getPilotName() {
        return pilotName;
    }

    public Integer getPoints() {
        return points;
    }
}
