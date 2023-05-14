module com.oop.patientmanagement {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.oop.patientmanagement to javafx.fxml;
    exports com.oop.patientmanagement;
    exports com.oop.patientmanagement.Model;
    opens com.oop.patientmanagement.Model to javafx.fxml;
    exports com.oop.patientmanagement.Controller;
    opens com.oop.patientmanagement.Controller to javafx.fxml;
}