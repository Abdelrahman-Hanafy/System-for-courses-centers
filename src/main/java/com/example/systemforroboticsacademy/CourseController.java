package com.example.systemforroboticsacademy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class CourseController extends StageController{

    @FXML
    TextField id;

    @FXML
    TextField nm;

    @FXML
    TextField info;

    @FXML
    TableView tableView;
    ObservableList<Course> c = FXCollections.observableArrayList(Course.courses);

    @FXML
    public void initialize(){

        TableColumn nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Course,String>("Name"));
        TableColumn idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<Course,String>("ID"));
        TableColumn InfoCol = new TableColumn<>("Info");
        InfoCol.setCellValueFactory(new PropertyValueFactory<Course,String>("Info"));


        tableView.setItems(c);
        tableView.getColumns().addAll(idCol,nameCol,InfoCol);
    }


    @FXML
    void onAddClick() throws SQLException {
        DataBase db = DataBase.CreateDataBase();
        Course c = new Course(id.getText(),nm.getText(),info.getText());
        db.Add(c,"course");
        Course.courses.add(c);
    }




}
