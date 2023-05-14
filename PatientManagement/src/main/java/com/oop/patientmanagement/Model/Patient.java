package com.oop.patientmanagement.Model;

public class Patient {
    private PatientInfo patientInfo;
    private PatientExamination patientExamination;

    public Patient(PatientInfo patientInfo, PatientExamination patientExamination) {
        this.patientInfo = patientInfo;
        this.patientExamination = patientExamination;
    }

    public PatientInfo getPatientInfo() {
        return patientInfo;
    }

    public void setPatientInfo(PatientInfo patientInfo) {
        this.patientInfo = patientInfo;
    }

    public PatientExamination getPatientExamination() {
        return patientExamination;
    }

    public void setPatientExamination(PatientExamination patientExamination) {
        this.patientExamination = patientExamination;
    }
}
