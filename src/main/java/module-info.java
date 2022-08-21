module com.example.systemforroboticsacademy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.security.jgss;
    requires java.desktop;


    opens com.example.systemforroboticsacademy to javafx.fxml;
    exports com.example.systemforroboticsacademy;
}