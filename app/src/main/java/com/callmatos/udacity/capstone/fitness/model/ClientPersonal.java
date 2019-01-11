package com.callmatos.udacity.capstone.fitness.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "clientpersonal")
public class ClientPersonal implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String name;

    private String locationName;

    private int hour;

    private int minute;

    private double latitude;

    private double longitude;

    private String address;

    private String detalheGoal;

    private String detalheGym;

    private double latitude;

    private double longitude;

    private String address;


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ClientPersonal(){}

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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getDetalheGoal() {
        return detalheGoal;
    }

    public String getDetalheGym() {
        return detalheGym;
    }

    public void setDetalheGym(String detalheGym) {
        this.detalheGym = detalheGym;
    }

    public void setDetalheGoal(String detalheGoal) {
        this.detalheGoal = detalheGoal;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
