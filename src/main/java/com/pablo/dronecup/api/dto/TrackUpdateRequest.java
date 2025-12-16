package com.pablo.dronecup.api.dto;

import java.util.Objects;

public class TrackUpdateRequest {

    private String name;
    private String location;

    public TrackUpdateRequest() {
    }

    public TrackUpdateRequest(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrackUpdateRequest that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }

    @Override
    public String toString() {
        return "TrackUpdateRequest{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
