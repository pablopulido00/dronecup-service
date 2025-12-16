package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class ChampionshipResponse {

    private Long id;
    private String name;
    private Integer seasonYear;

    public ChampionshipResponse() {
    }

    public ChampionshipResponse(Long id, String name, Integer seasonYear) {
        this.id = id;
        this.name = name;
        this.seasonYear = seasonYear;
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

    public Integer getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(Integer seasonYear) {
        this.seasonYear = seasonYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o ) return true;
        if (!(o instanceof ChampionshipResponse that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ChampionshipResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seasonYear=" + seasonYear +
                '}';
    }
}
