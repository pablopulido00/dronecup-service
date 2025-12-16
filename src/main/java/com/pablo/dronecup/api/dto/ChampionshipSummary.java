package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class ChampionshipSummary {

    private Long id;
    private String name;
    private Integer seasonYear;

    public ChampionshipSummary() {
    }

    public ChampionshipSummary(Long id, String name, Integer seasonYear) {
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
        if (!(o instanceof ChampionshipSummary that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ChampionshipSummary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seasonYear=" + seasonYear +
                '}';
    }
}
