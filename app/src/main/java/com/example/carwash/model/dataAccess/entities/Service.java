package com.example.carwash.model.dataAccess.entities;

public class Service {
    private String name,price;
    private boolean isChecked = false;

    public Service(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
