package com.example.systemforroboticsacademy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginApplication extends Application {
    private static Scene scene;
    private static  Stage stg;
    public static void setScene(Parent p) {
        scene = new Scene(p);
    }

    public static void UpdateStage(){
        stg.setScene(scene);
        stg.show();
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        stg = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("Login.fxml"));
        scene = new Scene(fxmlLoader.load());
        stg.setTitle("Robokid");
        stg.setScene(scene);
        stg.show();

    }

    public static void main(String[] args) {
        launch();
    }
}