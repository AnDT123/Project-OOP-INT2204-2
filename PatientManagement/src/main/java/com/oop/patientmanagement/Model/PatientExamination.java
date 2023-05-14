package com.oop.patientmanagement.Model;

import java.util.ArrayList;

public class PatientExamination {
    private ArrayList<Disease> diseasesList = new ArrayList<>();
    private String date;

    public ArrayList<Disease> getDiseasesList() {
        return diseasesList;
    }

    public void setDiseasesList(ArrayList<Disease> diseasesList) {
        this.diseasesList = diseasesList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PatientExamination(ArrayList<Disease> diseasesList, String date) {
        this.diseasesList = diseasesList;
        this.date = date;
    }
}
