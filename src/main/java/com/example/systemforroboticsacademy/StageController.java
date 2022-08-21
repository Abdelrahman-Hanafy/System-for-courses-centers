package com.example.systemforroboticsacademy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.sql.SQLException;

public class StageController {

    private void updateStage(String file) throws IOException {
        LoginApplication.setScene((new FXMLLoader(getClass().getResource(file))).load());
        LoginApplication.UpdateStage();
    }

    @FXML
    void onStudentButtonClick() throws IOException {
        updateStage("Student.fxml");
    }

    @FXML
    void onWorkerButtonClick() throws IOException {
        updateStage("Worker.fxml");
    }

    @FXML
    void onSessionButtonClick() throws IOException {
        updateStage("Session.fxml");
    }

    @FXML
    void onRecietButtonClick() throws IOException {
        updateStage("Reciet.fxml");
    }

    @FXML
    void onCantineButtonClick() throws IOException {
        updateStage("Cantine.fxml");
    }

    @FXML
    void onGroupButtonClick() throws IOException {
        updateStage("Group.fxml");
    }

    @FXML
    void onCourseButtonClick() throws IOException {
        updateStage("Course.fxml");
    }

    @FXML
    void onLogoutClick() throws SQLException {
        try {
            LoginApplication.setScene((new FXMLLoader(getClass().getResource("Login.fxml"))).load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        DataBase db = DataBase.CreateDataBase();

        for(CantineItem i:Cantine.CreateCantine().items){
            db.Update(i.getID(),"cantine","Quantity",i.count());
        }

        LoginApplication.UpdateStage();

    }




}
