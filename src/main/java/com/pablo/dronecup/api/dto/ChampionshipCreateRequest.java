package com.pablo.dronecup.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class ChampionshipCreateRequest {

    @NotBlank
    private String name;

    @NotNull
    @Min(1900)
    @Max(2100)
    private Integer seasonYear;

    public ChampionshipCreateRequest() {
    }

    public ChampionshipCreateRequest(String name, Integer seasonYear) {
        this.name = name;
        this.seasonYear = seasonYear;
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
        if (!(o instanceof ChampionshipCreateRequest that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(seasonYear, that.seasonYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, seasonYear);
    }

    @Override
    public String toString() {
        return "ChampionshipCreateRequest{" +
                "name='" + name + '\'' +
                ", seasonYear=" + seasonYear +
                '}';
    }
}
