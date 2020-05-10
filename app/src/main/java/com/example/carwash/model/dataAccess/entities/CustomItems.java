package com.example.carwash.model.dataAccess.entities;

public class CustomItems {
    private  String spinnerText;

    public CustomItems(String spinnerText) {
        this.spinnerText = spinnerText;
    }

    public String getSpinnerText() {
        return spinnerText;
    }

    public void setSpinnerText(String spinnerText) {
        this.spinnerText = spinnerText;
    }
}
