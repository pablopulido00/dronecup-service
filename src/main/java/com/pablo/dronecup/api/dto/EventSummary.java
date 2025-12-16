package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class EventSummary {

    private Long id;
    private String name;

    public EventSummary() {
    }

    public EventSummary(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventSummary that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EventSummary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
