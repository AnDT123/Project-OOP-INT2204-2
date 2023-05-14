package com.oop.patientmanagement.Model;

public class Disease {
    private String diseaseName;
    private String advice;
    private String medicine;

    public Disease(String diseaseName, String medicine, String advice) {
        this.diseaseName = diseaseName;
        this.advice = advice;
        this.medicine = medicine;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }
}
