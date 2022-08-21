package com.example.systemforroboticsacademy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ReciteController extends StageController{

    @FXML
    TextField nm;

    @FXML
    TextField fee;

    @FXML
    TextField Com;

    @FXML
    TextField id;

    @FXML
    ComboBox<String> typs;

    ObservableList<String> types = FXCollections.observableArrayList("Month","Competion","Kit","Other");

    @FXML
    public void initialize(){

        typs.setItems(types);
    }

    @FXML
    void onPayClick() throws SQLException {
        Recite r;
        Student s = Student.getInstance(nm.getText());
        r = new Recite(s,id.getText(),typs.getSelectionModel().getSelectedItem(),
                Com.getText(),Integer.parseInt(fee.getText()));

        r.pay();

    }



}
