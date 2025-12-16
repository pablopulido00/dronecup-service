package com.pablo.dronecup.api.dto;

import java.util.List;
import java.util.Objects;

public class HeatResponse {

    private Long id;
    private String name;
    private Integer number;
    private Long eventId;
    private List<HeatEntryResponse> heatEntries;

    public HeatResponse() {
    }

    public HeatResponse(Long id, String name, Integer number, Long eventId, List<HeatEntryResponse> heatEntries) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.eventId = eventId;
        this.heatEntries = heatEntries;
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

    public List<HeatEntryResponse> getHeatEntries() {
        return heatEntries;
    }

    public void setHeatEntries(List<HeatEntryResponse> heatEntries) {
        this.heatEntries = heatEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeatResponse that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "HeatResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", eventId=" + eventId +
                ", heatEntriesCount=" + (heatEntries != null ? heatEntries.size() : 0) +
                '}';
    }
}
