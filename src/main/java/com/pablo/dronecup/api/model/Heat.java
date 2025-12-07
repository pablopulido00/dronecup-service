package com.pablo.dronecup.api.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "heats")
public class Heat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @OneToMany(mappedBy = "heat")
    private List<HeatEntry> heatEntries = new ArrayList<>();

    public Heat() {
    }

    public Heat(String name, Integer number) {
        this.name = name;
        this.number = number;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<HeatEntry> getHeatEntries() {
        return heatEntries;
    }

    public void setHeatEntries(List<HeatEntry> heatEntries) {
        this.heatEntries = heatEntries;
    }

    @Override
    public String toString() {
        return "Heat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
