package com.oop.patientmanagement.Model;

public class PatientInfo {
    private String name;
    private String dateOfBirth;
    private String citizenID;
    private String gender;
    private String address;

    public PatientInfo() {

    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public PatientInfo(String name, String dateOfBirth, String citizenID, String gender, String address) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.citizenID = citizenID;
        this.gender = gender;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCitizenID() {
        return citizenID;
    }

    public void setCitizenID(String citizenID) {
        this.citizenID = citizenID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
