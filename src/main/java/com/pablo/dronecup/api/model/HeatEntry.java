package com.pablo.dronecup.api.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "heat_entries")
public class HeatEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer startPosition;

    @ManyToOne
    @JoinColumn(name = "pilot_id", nullable = false)
    private Pilot pilot;

    @ManyToOne
    @JoinColumn(name = "heat_id", nullable = false)
    private Heat heat;

    @OneToOne(mappedBy = "heatEntry")
    private HeatResult heatResult;

    public HeatEntry() {
    }

    public HeatEntry(Integer startPosition, Pilot pilot, Heat heat) {
        this.startPosition = startPosition;
        this.pilot = pilot;
        this.heat = heat;
    }

    public Long getId() {
        return id;
    }

    public Integer getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Integer startPosition) {
        this.startPosition = startPosition;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public Heat getHeat() {
        return heat;
    }

    public void setHeat(Heat heat) {
        this.heat = heat;
    }

    public HeatResult getHeatResult() {
        return heatResult;
    }

    public void setHeatResult(HeatResult heatResult) {
        this.heatResult = heatResult;
    }

    @Override
    public String toString() {
        return "HeatEntry{" +
                "id=" + id +
                ", startPosition=" + startPosition +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof HeatEntry that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }
}
