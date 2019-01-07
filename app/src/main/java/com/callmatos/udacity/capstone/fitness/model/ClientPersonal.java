package com.callmatos.udacity.capstone.fitness.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "clientpersonal")
public class ClientPersonal implements Serializable {


    @PrimaryKey
    private Integer id;

    private String name;

    private String locationName;

    private long timeWorkout;

    private int totalWorkout;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationName() {
        return locationName;
    }

    public int getTotalWorkout() {
        return totalWorkout;
    }

    public void setTotalWorkout(int totalWorkout) {
        this.totalWorkout = totalWorkout;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public long getTimeWorkout() {
        return timeWorkout;
    }

    public void setTimeWorkout(long timeWorkout) {
        this.timeWorkout = timeWorkout;
    }
}
