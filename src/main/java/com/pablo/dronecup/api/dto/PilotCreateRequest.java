package com.pablo.dronecup.api.dto;

import com.pablo.dronecup.api.model.Pilot;

import java.util.Objects;

public class PilotCreateRequest {

    private String name;
    private String nationality;
    private Integer age;
    private Long teamId;
    private Long droneId;

    public PilotCreateRequest (){}

    public PilotCreateRequest(String name, String nationality, Integer age, Long teamId, Long droneId) {
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
    public boolean equals (Object o){
        if (this == o ) return true;
        if (!(o instanceof PilotCreateRequest pilotCreateRequest)) return false;
        return Objects.equals(name, pilotCreateRequest.name ) && Objects.equals(nationality, pilotCreateRequest.nationality)
                && Objects.equals(age, pilotCreateRequest.age)
                && Objects.equals(teamId, pilotCreateRequest.teamId)
                && Objects.equals(droneId, pilotCreateRequest.droneId);



    }

    @Override
    public int hashCode (){
        return Objects.hash(name,nationality, age, teamId, droneId );
    }

    @Override
    public String toString() {
        return "PilotCreateRequest{" +
                "name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", age=" + age +
                ", teamId=" + teamId +
                ", droneId=" + droneId +
                '}';
    }
}
