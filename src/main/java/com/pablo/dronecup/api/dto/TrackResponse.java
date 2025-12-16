package com.pablo.dronecup.api.dto;

import java.util.List;
import java.util.Objects;

public class TrackResponse {

    private Long id;
    private String name;
    private String location;
    private List<EventSummary> events;

    public TrackResponse() {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<EventSummary> getEvents() {
        return events;
    }

    public void setEvents(List<EventSummary> events) {
        this.events = events;
    }

    public TrackResponse(Long id, String name, String location, List<EventSummary> events) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrackResponse that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TrackResponse{" +
                "eventsCount=" +  (events != null ? events.size() : 0) +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
