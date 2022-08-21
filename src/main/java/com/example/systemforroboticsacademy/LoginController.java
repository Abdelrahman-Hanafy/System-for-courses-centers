package com.example.systemforroboticsacademy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField user;

    @FXML
    private PasswordField Pass;


    @FXML
    public void initialize() throws SQLException {
        Worker.Generate();
        Course.Generate();
        Group.Generate();
        Student.Generate();
    }


    @FXML
    void onLoginButtonClick() throws IOException, SQLException {

        String us = user.getText(), pass = Pass.getText();
        boolean IN = false;
        if(pass.equals("0000") && us.equals("root")){
            IN =true;

        }else if(us.length() !=0){

            DataBase db = DataBase.CreateDataBase();
            ResultSet res = db.Load("worker","\"%Secertary%\"");
            while (res != null && res.next()){
                String[] name = res.getString("Name").split(" ");
                String[] id = res.getString("ID").split("-");
                if(name[0].equals(us)&&id[0].equals(pass)) IN=true;
            }
        }

        if(!IN){
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Login Faild");
            al.setContentText("Password does not match User");
            al.showAndWait();
        }else{
            LoginApplication.setScene((new FXMLLoader(getClass().getResource("Student.fxml"))).load());
            LoginApplication.UpdateStage();
        }

    }

}