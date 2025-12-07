package com.pablo.dronecup.api.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "standings")
public class Standing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer points;

    @ManyToOne
    @JoinColumn(name = "championship_id", nullable = false)
    private Championship championship;

    @OneToOne
    @JoinColumn(name = "pilot_id", nullable = false)
    private Pilot pilot;

    public Standing() {
    }

    public Standing(Integer points, Championship championship, Pilot pilot) {
        this.points = points;
        this.championship = championship;
        this.pilot = pilot;
    }

    public Long getId() {
        return id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    @Override
    public String toString() {
        return "Standing{" +
                "id=" + id +
                ", points=" + points +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Standing standing)) return false;
        return Objects.equals(id, standing.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
