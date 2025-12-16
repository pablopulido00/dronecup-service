package com.pablo.dronecup.api.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class EventResponse {

    private Long id;
    private String name;
    private LocalDate date;
    private TrackSummary track;
    private List<HeatSummary> heats;


    public EventResponse() {

    }

    public EventResponse(Long id, String name, LocalDate date, TrackSummary track, List<HeatSummary> heats) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.track = track;
        this.heats = heats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TrackSummary getTrack() {
        return track;
    }

    public void setTrack(TrackSummary track) {
        this.track = track;
    }

    public List<HeatSummary> getHeats() {
        return heats;
    }

    public void setHeats(List<HeatSummary> heats) {
        this.heats = heats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventResponse that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EventResponse{" +
                "heatsCount=" + (heats != null ? heats.size() : 0 )+
                ", track=" + track +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
