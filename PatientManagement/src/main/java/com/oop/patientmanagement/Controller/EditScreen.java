package com.oop.patientmanagement.Controller;

import com.oop.patientmanagement.Application;
import com.oop.patientmanagement.Model.PatientInfo;
import com.oop.patientmanagement.Model.PatientManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditScreen implements Initializable {
    public TextField tfPatientName;
    public TextField tfDateOfBirth;
    public TextField tfGender;
    public TextField tfCitizenID;
    public TextField tf_searchByCitizenID;
    public TextField tfAddress;
    public Text textShowResults;
    public Text textNoResults;
    public Text textAdvice;
    public Text textMedicine;
    public Text textDiseaseName;
    public Text textDateTime;
    public Button btnComplete;
    public Text textPatientName;
    public Text textDateOfBirth;
    public Text textGender;
    public Text textCitizenID;
    public Text textAddress;
    public Text textShowInform;
    public TextField tfDateTime0;
    public TextField tfDateTime1;
    public TextField tfDateTime2;
    public TextArea tfDiseaseInformation0;
    public TextArea tfDiseaseInformation1;
    public TextArea tfDiseaseInformation2;
    public TextArea[] textAreaArrayList = new TextArea[3];
    public TextField[] textFields = new TextField[3];
    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean visible;
    private int count = 0;
    private final int max = 3;

    public void BackToMain(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("home_screen.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("HỆ THỐNG QUẢN LÝ BỆNH NHÂN!");
        stage.setScene(scene);
        stage.show();
    }

    public void showAndHideResult() {
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
        btnComplete.setVisible(visible);
    }

    public void complete(ActionEvent actionEvent) {
        boolean check = false;
        if (tfAddress.getText().isEmpty() || tfGender.getText().isEmpty() || tfCitizenID.getText().isEmpty()
                || tfDateOfBirth.getText().isEmpty() || tfPatientName.getText().isEmpty()) {
            alertError();
        } else {
            for (int i = 0; i < count; i++) {
                if (textFields[i].getText().isEmpty() && !textAreaArrayList[i].getText().isEmpty() ||
                        !textFields[i].getText().isEmpty() && textAreaArrayList[i].getText().isEmpty()) {
                    check = true;
                }
            }
            if (check) {
                alertError();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Hộp thoại xác nhận");
                alert.setHeaderText("Xác nhận");
                alert.setContentText("Bạn đã chắc chắn nhập đúng thông tin chưa?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    String name = tfPatientName.getText();
                    String dateOfBirth = tfDateOfBirth.getText();
                    String citizenID = tfCitizenID.getText();
                    String gender = tfGender.getText();
                    String address = tfAddress.getText();
                    PatientInfo patientInfo = new PatientInfo(name, dateOfBirth, citizenID, gender, address);

                    PatientManagement.management.removePatient(tf_searchByCitizenID.getText());
                    for (int i = 0; i < count; i++) {
                        if (!textFields[i].getText().isEmpty()) {
                            PatientManagement.management.writeDataToFile(patientInfo,
                                    textFields[i].getText(), textAreaArrayList[i].getText().replace("\n", " - "));
                        }
                    }
                    PatientManagement.management.readDataFromFile();
                    alertSuccess();
                }
            }
        }
    }

    public void alertSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hộp thoại thông báo");
        alert.setHeaderText("Thông báo");
        alert.setContentText("Chỉnh sửa thông tin thành công!");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            refresh();
        }
    }

    public void refresh() {
        visible = false;
        showAndHideResult();
        showAndHideTextField();
        textNoResults.setVisible(visible);
        tf_searchByCitizenID.setText("");
    }

    public void alertError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hộp thoại thông báo");
        alert.setHeaderText("Thông báo");
        alert.setContentText("Bạn nhập thiếu thông tin!");
        alert.show();
    }

    public void SearchInformationPatient(ActionEvent actionEvent) {
        String citizenID = tf_searchByCitizenID.getText();
        count = PatientManagement.management.count(citizenID);
        if (!citizenID.isEmpty() && count != 0) {
            visible = true;

            PatientInfo patientInfo = PatientManagement.management.searchPatientInfo(citizenID);
            tfPatientName.setText(patientInfo.getName());
            tfDateOfBirth.setText(patientInfo.getDateOfBirth());
            tfGender.setText(patientInfo.getGender());
            tfCitizenID.setText(patientInfo.getCitizenID());
            tfAddress.setText(patientInfo.getAddress());

            String[] result = PatientManagement.management.stringData(citizenID).split("\t");
            for (int i = 0; i < count; i++) {
                String date = result[i].split("/!/")[0];
                textFields[i].setText(date);
                String diseaseInfo = result[i].split("/!/")[1];
                textAreaArrayList[i].setText(diseaseInfo);
            }
        } else {
            visible = false;
        }
        showAndHideResult();
        showAndHideTextField();
    }

    public void showAndHideTextField() {
        tfPatientName.setVisible(visible);
        tfDateOfBirth.setVisible(visible);
        tfCitizenID.setVisible(visible);
        tfGender.setVisible(visible);
        tfAddress.setVisible(visible);

        for (int i = 0; i < count; i++) {
            textFields[i].setVisible(visible);
        }
        for (int i = count; i < max; i++) {
            textFields[i].setVisible(false);
        }
        for (int i = 0; i < count; i++) {
            textAreaArrayList[i].setVisible(visible);
        }
        for (int i = count; i < max; i++) {
            textAreaArrayList[i].setVisible(false);
        }
    }

    public void setUp() {
        textFields[0] = tfDateTime0;
        textFields[1] = tfDateTime1;
        textFields[2] = tfDateTime2;

        textAreaArrayList[0] = tfDiseaseInformation0;
        textAreaArrayList[1] = tfDiseaseInformation1;
        textAreaArrayList[2] = tfDiseaseInformation2;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUp();
    }
}
