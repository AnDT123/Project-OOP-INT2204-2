package com.oop.patientmanagement.Controller;

import com.oop.patientmanagement.Application;
import com.oop.patientmanagement.Model.PatientInfo;
import com.oop.patientmanagement.Model.PatientManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class AddScreen {

    public TextField tfPatientName;
    public TextField tfDateOfBirth;
    public TextField tfGender;
    public TextField tfCitizenID;
    public TextField tfAddress;
    public TextField tfDateTime;
    public TextArea tfDiseaseInformation;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void BackToMain(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("home_screen.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("HỆ THỐNG QUẢN LÝ BỆNH NHÂN!");
        stage.setScene(scene);
        stage.show();
    }

    public void complete() {
        if (tfAddress.getText().isEmpty() || tfDiseaseInformation.getText().isEmpty() || tfGender.getText().isEmpty()
                || tfCitizenID.getText().isEmpty() || tfDateTime.getText().isEmpty() || tfDateOfBirth.getText().isEmpty()
                || tfPatientName.getText().isEmpty()) {
            alertEmptyError();
        } else if (tfDiseaseInformation.getText().replace("\n", " - ").split(" - ").length % 3 != 0) {
            alertWrongFormatError();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Hộp thoại xác nhận");
            alert.setHeaderText("Xác nhận");
            alert.setContentText("Bạn đã chắc chắn nhập đúng thông tin chưa?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                PatientInfo patientInfo = new PatientInfo(tfPatientName.getText(), tfDateOfBirth.getText(),
                        tfCitizenID.getText(), tfGender.getText(), tfAddress.getText());
                String dateTime = tfDateTime.getText();
                String diseaseInfo = tfDiseaseInformation.getText().replace("\n", " - ");
                PatientManagement.management.writeDataToFile(patientInfo, dateTime, diseaseInfo);
                PatientManagement.management.readDataFromFile();
                alertSuccess();
            }
        }
    }

    public void alertSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hộp thoại thông báo");
        alert.setHeaderText("Thông báo");
        alert.setContentText("Nhập thông tin thành công!");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            refresh();
        }
    }

    public void alertEmptyError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hộp thoại thông báo");
        alert.setHeaderText("Thông báo");
        alert.setContentText("Bạn nhập thiếu thông tin!");
        alert.show();
    }

    public void alertWrongFormatError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hộp thoại thông báo");
        alert.setHeaderText("Thông báo");
        alert.setContentText("Bạn nhập sai cú pháp!");
        alert.show();
    }

    public void refresh() {
        tfPatientName.setText("");
        tfDateOfBirth.setText("");
        tfGender.setText("");
        tfCitizenID.setText("");
        tfAddress.setText("");
        tfDateTime.setText("");
        tfDiseaseInformation.setText("");
    }

}
