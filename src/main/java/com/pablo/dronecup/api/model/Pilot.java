package com.pablo.dronecup.api.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pilots")
public class Pilot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String nationality;

    @Column(nullable = false)
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne
    @JoinColumn(name = "drone_id", nullable = false)
    private Drone drone;

    @OneToOne(mappedBy = "pilot")
    private Standing standing;

    @OneToMany(mappedBy = "pilot")
    private List<HeatEntry> heatEntries = new ArrayList<>();

    public Pilot() {
    }

    public Pilot(String name, String nationality, Integer age) {
        this.name = name;
        this.nationality = nationality;
        this.age = age;
    }

    public Long getId() {
        return id;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public Standing getStanding() {
        return standing;
    }

    public void setStanding(Standing standing) {
        this.standing = standing;
    }

    public List<HeatEntry> getHeatEntries() {
        return heatEntries;
    }

    public void setHeatEntries(List<HeatEntry> heatEntries) {
        this.heatEntries = heatEntries;
    }

    @Override
    public String toString() {
        return "Pilot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pilot pilot)) return false;
        return Objects.equals(id, pilot.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

