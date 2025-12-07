package com.pablo.dronecup.api.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "heat_results")
public class HeatResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer position;

    @Column(nullable = false)
    private Double bestLapTime;

    @Column(nullable = false)
    private Double totalTime;

    @Column(nullable = false)
    private Integer penalties;

    @OneToOne
    @JoinColumn(name = "heat_entry_id", nullable = false)
    private HeatEntry heatEntry;

    public HeatResult() {
    }

    public HeatResult(Integer position, Double bestLapTime, Double totalTime,
                      Integer penalties, HeatEntry heatEntry) {
        this.position = position;
        this.bestLapTime = bestLapTime;
        this.totalTime = totalTime;
        this.penalties = penalties;
        this.heatEntry = heatEntry;
    }

    public Long getId() {
        return id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Double getBestLapTime() {
        return bestLapTime;
    }

    public void setBestLapTime(Double bestLapTime) {
        this.bestLapTime = bestLapTime;
    }

    public Double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getPenalties() {
        return penalties;
    }

    public void setPenalties(Integer penalties) {
        this.penalties = penalties;
    }

    public HeatEntry getHeatEntry() {
        return heatEntry;
    }

    public void setHeatEntry(HeatEntry heatEntry) {
        this.heatEntry = heatEntry;
    }

    @Override
    public String toString() {
        return "HeatResult{" +
                "id=" + id +
                ", position=" + position +
                ", bestLapTime=" + bestLapTime +
                ", totalTime=" + totalTime +
                ", penalties=" + penalties +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof HeatResult that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }
}
