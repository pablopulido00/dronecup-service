package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class PilotResponse {

    private Long id;
    private String name;
    private String nationality;
    private Integer age;
    private TeamSummary team;
    private DroneSummary drone;

    public PilotResponse(){}

    public PilotResponse(Long id, String name, String nationality, Integer age, TeamSummary team, DroneSummary drone) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.age = age;
        this.team = team;
        this.drone = drone;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public TeamSummary getTeam() {
        return team;
    }

    public void setTeam(TeamSummary team) {
        this.team = team;
    }

    public DroneSummary getDrone() {
        return drone;
    }

    public void setDrone(DroneSummary drone) {
        this.drone = drone;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if(!(o instanceof PilotResponse pilotResponse )) return false;
        return Objects.equals(id, pilotResponse.id);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PilotResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", age=" + age +
                ", team=" + team +
                ", drone=" + drone +
                '}';
    }
}
