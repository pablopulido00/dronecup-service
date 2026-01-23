package com.pablo.dronecup.api.dto;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;

public class EventUpdateRequest {


    @Size(min = 1, max = 100)
    private String name;

    @PastOrPresent
    private LocalDate date;

    @Positive
    private Long trackId;

    public EventUpdateRequest() {
    }

    public EventUpdateRequest(String name, LocalDate date, Long trackId) {
        this.name = name;
        this.date = date;
        this.trackId = trackId;
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

    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventUpdateRequest that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(date, that.date) && Objects.equals(trackId, that.trackId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, trackId);
    }

    @Override
    public String toString() {
        return "EventCreateRequest{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", trackId=" + trackId +
                '}';
    }
}
