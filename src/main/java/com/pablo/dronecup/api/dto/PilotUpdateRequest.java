package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class PilotUpdateRequest {

    private String name;
    private String nationality;
    private Integer age;
    private Long teamId;
    private Long droneId;

    public PilotUpdateRequest(){}

    public PilotUpdateRequest(String name, String nationality, Integer age, Long teamId, Long droneId) {
        this.name = name;
        this.nationality = nationality;
        this.age = age;
        this.teamId = teamId;
        this.droneId = droneId;
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

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getDroneId() {
        return droneId;
    }

    public void setDroneId(Long droneId) {
        this.droneId = droneId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PilotUpdateRequest that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(nationality, that.nationality) && Objects.equals(age, that.age) && Objects.equals(teamId, that.teamId) && Objects.equals(droneId, that.droneId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nationality, age, teamId, droneId);
    }

    @Override
    public String toString() {
        return "PilotUpdateRequest{" +
                "name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", age=" + age +
                ", teamId=" + teamId +
                ", droneId=" + droneId +
                '}';
    }
}
