package com.example.systemforroboticsacademy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class WorkerController extends StageController {

    @FXML
    ComboBox<String> types;

    @FXML
    TextField nm;
    @FXML
    TextField nid;
    @FXML
    TextField phone;
    @FXML
    TextField monye;
    @FXML
    TextField id;

    @FXML
    TableView tableView;

    ObservableList<String> typs = FXCollections.observableArrayList("Instructor","Assistance","Secertary");
    ObservableList<Worker> w = FXCollections.observableArrayList(Worker.workers);

    DataBase db ;


    @FXML
    public void initialize(){
        types.setValue("Secertary");
        types.setItems(typs);


        TableColumn nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Worker,String>("Name"));
        TableColumn idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<Worker,String>("ID"));
        TableColumn DurationCol = new TableColumn<>("Duration");
        DurationCol.setCellValueFactory(new PropertyValueFactory<Worker,Integer>("Duration"));


        tableView.setItems(w);
        tableView.getColumns().addAll(idCol,nameCol,DurationCol);
    }

    @FXML
    void onAddClick() {

        WorkerFactory wf = new WorkerFactory();
        String type = types.getSelectionModel().getSelectedItem();
        Worker w = wf.CreateWorker(type,id.getText(),
                nm.getText(),phone.getText(),
                nid.getText(),Integer.parseInt(monye.getText()),0);


        try {
            db = DataBase.CreateDataBase();
            db.Add(w,"Worker");
            Worker.workers.add(w);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
