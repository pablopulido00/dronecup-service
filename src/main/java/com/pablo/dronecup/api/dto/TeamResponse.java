package com.pablo.dronecup.api.dto;

import java.util.List;
import java.util.Objects;

public class TeamResponse {

    private Long id;
    private String name;
    private String country;
    private List<PilotSummary> pilots;

    public TeamResponse (){}

    public TeamResponse(Long id, String name, String country, List<PilotSummary> pilots) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.pilots = pilots;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<PilotSummary> getPilots() {
        return pilots;
    }

    public void setPilots(List<PilotSummary> pilots) {
        this.pilots = pilots;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamResponse that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


    @Override
    public String toString() {
        return "TeamResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", pilotsCount=" + (pilots != null ? pilots.size() : 0) +
                '}';

    }


}
