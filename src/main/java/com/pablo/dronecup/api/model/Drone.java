package com.pablo.dronecup.api.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "drones")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String model;

    @Column(length = 100)
    private String manufacturer; // ← corregido

    @OneToOne(mappedBy = "drone")
    private Pilot pilot;

    public Drone() {
    }

    public Drone(String model, String manufacturer) {
        this.model = model;
        this.manufacturer = manufacturer;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Drone drone)) return false;
        return Objects.equals(id, drone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", model='" + model + '\'' +
                '}';
    }
}
