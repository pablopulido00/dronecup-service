package com.pablo.dronecup.api.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "championships")
public class Championship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Integer seasonYear;

    @OneToMany(mappedBy = "championship")
    private List<Standing> standings = new ArrayList<>();

    public Championship() {
    }

    public Championship(String name, Integer seasonYear) {
        this.name = name;
        this.seasonYear = seasonYear;
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

    public Integer getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(Integer seasonYear) {
        this.seasonYear = seasonYear;
    }

    public List<Standing> getStandings() {
        return standings;
    }

    public void setStandings(List<Standing> standings) {
        this.standings = standings;
    }

    @Override
    public String toString() {
        return "Championship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seasonYear=" + seasonYear +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Championship championship)) return false;
        return Objects.equals(id, championship.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
