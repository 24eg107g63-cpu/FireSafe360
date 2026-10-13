package com.firesafe.firesafe360.entity;

import jakarta.persistence.*;

@Entity
public class FireStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stationName;
    private String location;
    private int fireEngines;
    private int firefighters;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getFireEngines() {
        return fireEngines;
    }

    public void setFireEngines(int fireEngines) {
        this.fireEngines = fireEngines;
    }

    public int getFirefighters() {
        return firefighters;
    }

    public void setFirefighters(int firefighters) {
        this.firefighters = firefighters;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}