package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class HeatCreateRequest {

    private String name;
    private Integer number;
    private Long eventId;

    public HeatCreateRequest() {

    }

    public HeatCreateRequest(String name, Integer number, Long eventId) {
        this.name = name;
        this.number = number;
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeatCreateRequest that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(number, that.number) && Objects.equals(eventId, that.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number, eventId);
    }

    @Override
    public String toString() {
        return "HeatCreateRequest{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", eventId=" + eventId +
                '}';
    }
}
