package com.oop.patientmanagement.Model;

import java.io.*;
import java.util.ArrayList;

public class PatientManagement {

    public static PatientManagement management = new PatientManagement();

    private ArrayList<Patient> patientArrayList = new ArrayList<>();

    public void addPatient(Patient patient) {
        patientArrayList.add(patient);
    }

    public String searchPatient(String citizenID) {
        StringBuilder result = new StringBuilder();
        for (Patient patient : patientArrayList) {
            if (patient.getPatientInfo().getCitizenID().equals(citizenID)) {
                for (int i = 0; i < patient.getPatientExamination().getDiseasesList().size(); i++) {
                    result.append(patient.getPatientExamination().getDate())
                            .append(" \t\t")
                            .append(patient.getPatientExamination().getDiseasesList().get(i).getDiseaseName())
                            .append(" \t\t  ")
                            .append(patient.getPatientExamination().getDiseasesList().get(i).getMedicine())
                            .append(" \t\t  ")
                            .append(patient.getPatientExamination().getDiseasesList().get(i).getAdvice())
                            .append(" \n");
                }
            }
        }
        return result.toString();
    }

    public PatientInfo searchPatientInfo(String citizenID) {
        PatientInfo result = new PatientInfo();
        for (Patient patient : patientArrayList) {
            if (patient.getPatientInfo().getCitizenID().equals(citizenID)) {
                result = patient.getPatientInfo();
                return result;
            }
        }
        return result;
    }

    public String stringData(String citizenID) {
        StringBuilder result = new StringBuilder();
        for (Patient patient : patientArrayList) {
            if (patient.getPatientInfo().getCitizenID().equals(citizenID)) {
                result.append(patient.getPatientExamination().getDate())
                        .append("/!/");
                for (int i = 0; i < patient.getPatientExamination().getDiseasesList().size(); i++) {
                    result.append(patient.getPatientExamination().getDiseasesList().get(i).getDiseaseName())
                            .append(" - ")
                            .append(patient.getPatientExamination().getDiseasesList().get(i).getMedicine())
                            .append(" - ")
                            .append(patient.getPatientExamination().getDiseasesList().get(i).getAdvice())
                            .append("\n");
                }
                result = new StringBuilder(result.substring(0, result.length() - 1));
                result.append("\t");
            }
        }
        return result.toString();
    }

    public int count(String citizenID) {
        int count = 0;
        for (Patient patient : patientArrayList) {
            if (patient.getPatientInfo().getCitizenID().equals(citizenID)) {
                count++;
            }
        }
        return count;
    }

    public void editPatient(String citizenID) {
        removePatient(citizenID);
    }

    public void removePatient(String citizenID) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Data.txt"));
            String line;
            StringBuilder Text = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (!line.split(" - ")[2].equals(citizenID)) {
                    Text.append(line).append("\n");
                }
            }
            Text = new StringBuilder(Text.substring(0, Text.length() - 1));
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/Data.txt"));
            writer.write(String.valueOf(Text));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readDataFromFile() {
        patientArrayList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/Data.txt"));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] data = line.split(" - ");
                PatientInfo patientInfo = new PatientInfo(data[0], data[1], data[2], data[3], data[4]);
                ArrayList<Disease> diseaseArrayList = new ArrayList<>();
                for (int i = 6; i < data.length; i += 3) {
                    diseaseArrayList.add(new Disease(data[i], data[i + 1], data[i + 2]));
                }
                PatientExamination patientExamination = new PatientExamination(diseaseArrayList, data[5]);
                patientArrayList.add(new Patient(patientInfo, patientExamination));
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDataToFile(PatientInfo patientInfo, String dateTime, String diseaseInfo) {
        try {
            Writer writer = new BufferedWriter(new FileWriter("src/main/resources/Data.txt", true));
            writer.append("\n");
            writer.append(patientInfo.getName());
            writer.append(" - ");
            writer.append(patientInfo.getDateOfBirth());
            writer.append(" - ");
            writer.append(patientInfo.getCitizenID());
            writer.append(" - ");
            writer.append(patientInfo.getGender());
            writer.append(" - ");
            writer.append(patientInfo.getAddress());
            writer.append(" - ");
            writer.append(dateTime);
            writer.append(" - ");
            writer.append(diseaseInfo);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
