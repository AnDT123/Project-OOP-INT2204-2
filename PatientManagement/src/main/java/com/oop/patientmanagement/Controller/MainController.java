package com.oop.patientmanagement.Controller;

import com.oop.patientmanagement.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void addPatient(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("add_screen.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("THÊM THÔNG TIN BỆNH NHÂN MỚI");
        stage.setScene(scene);
        stage.show();
    }

    public void editPatient(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("edit_screen.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("CHỈNH SỬA THÔNG TIN BỆNH NHÂN");
        stage.setScene(scene);
        stage.show();
    }

    public void removePatient(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("remove_screen.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("XOÁ THÔNG TIN BỆNH NHÂN");
        stage.setScene(scene);
        stage.show();
    }

    public void searchPatient(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("search_screen.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("TÌM KIẾM THÔNG TIN BỆNH NHÂN");
        stage.setScene(scene);
        stage.show();
    }

    public void exit() {
        System.exit(1);
    }
}