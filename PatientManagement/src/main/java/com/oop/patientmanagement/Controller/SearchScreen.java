package com.oop.patientmanagement.Controller;

import com.oop.patientmanagement.Application;
import com.oop.patientmanagement.Model.PatientInfo;
import com.oop.patientmanagement.Model.PatientManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SearchScreen {

    public TextField tf_searchByCitizenID;
    public Text textNoResults;
    public Text textShowResults;
    public Text textPatientName;
    public Text textDateOfBirth;
    public Text textGender;
    public Text textCitizenID;
    public Text textAddress;
    public Text textDateTime;
    public Text textAdvice;
    public Text textMedicine;
    public Text textDiseaseName;
    public Text textShowInform;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public boolean visible;

    public void BackToMain(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("home_screen.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("HỆ THỐNG QUẢN LÝ BỆNH NHÂN!");
        stage.setScene(scene);
        stage.show();
    }

    public void SearchInformationPatient(ActionEvent actionEvent) {
        String citizenID = tf_searchByCitizenID.getText();
        String string = PatientManagement.management.searchPatient(citizenID);
        if (!citizenID.isEmpty() && !string.equals("")) {
            visible = true;
            textShowInform.setText(string);
            PatientInfo patientInfo = PatientManagement.management.searchPatientInfo(citizenID);
            textPatientName.setText("Tên bệnh nhân:   \t" + patientInfo.getName());
            textDateOfBirth.setText("Ngày sinh:\t   " + patientInfo.getDateOfBirth());
            textGender.setText("Giới tính:\t   " + patientInfo.getGender());
            textCitizenID.setText("Số căn cước:\t   " + patientInfo.getCitizenID());
            textAddress.setText("Địa chỉ:   \t" + patientInfo.getAddress());
        } else {
            visible = false;
        }
        showResult();
    }

    public void showResult() {
        textNoResults.setVisible(!visible);
        textShowResults.setVisible(visible);
        textPatientName.setVisible(visible);
        textDateOfBirth.setVisible(visible);
        textGender.setVisible(visible);
        textCitizenID.setVisible(visible);
        textAddress.setVisible(visible);
        textDateTime.setVisible(visible);
        textAdvice.setVisible(visible);
        textMedicine.setVisible(visible);
        textDiseaseName.setVisible(visible);
        textShowInform.setVisible(visible);
        textShowInform.setLineSpacing(18.5);
    }

}
