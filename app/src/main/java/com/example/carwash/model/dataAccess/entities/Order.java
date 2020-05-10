package com.example.carwash.model.dataAccess.entities;

import java.util.ArrayList;

public class Order {
    User user;
    Car car;
    String date;
    ArrayList<Service> services;
    String totalAmount;

    public Order(User user, Car car, String date, ArrayList<Service> services, String totalAmount) {
        this.user = user;
        this.car = car;
        this.date = date;
        this.services = services;
        this.totalAmount = totalAmount;
    }

    public Order() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
