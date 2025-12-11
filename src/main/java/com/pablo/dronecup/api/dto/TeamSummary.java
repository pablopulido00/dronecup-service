package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class TeamSummary {

    private Long id;
    private String name;
    private String country;


    public TeamSummary() {
    }

    public TeamSummary(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
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


    @Override
    public boolean equals(Object o){
        if (this == o )return true;
        if (!(o instanceof TeamSummary teamSummary )) return false;
        return Objects.equals(id, teamSummary.id);

    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TeamSummary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }


}
