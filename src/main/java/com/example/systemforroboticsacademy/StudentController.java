package com.example.systemforroboticsacademy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;



import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentController extends StageController{
    @FXML
    TextField name;

    @FXML
    ComboBox<String> Groups;

    @FXML
    TextField id;

    @FXML
    ImageView iv;

    @FXML
    Button stream;

    @FXML
    TableView tableView;

    ObservableList<String> groups = FXCollections.observableArrayList();
    ObservableList<Student> s = FXCollections.observableArrayList(Student.students);


    @FXML
    public void initialize() throws SQLException {
        DataBase db = DataBase.CreateDataBase();
        ResultSet res = db.Load("Groups");
        while (res!=null&& res.next()){
            groups.add(res.getString("ID"));
        }

        Groups.setItems(groups);

        TableColumn nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Student,String>("Name"));
        TableColumn groupCol = new TableColumn<>("Group");
        groupCol.setCellValueFactory(new PropertyValueFactory<Student,String>("Group"));
        TableColumn sessionCol = new TableColumn<>("Session");
        sessionCol.setCellValueFactory(new PropertyValueFactory<Student,Integer>("Session"));
        TableColumn payCol = new TableColumn<>("isPay");
        payCol.setCellValueFactory(new PropertyValueFactory<Student,String>("Pay"));

        tableView.setItems(s);
        tableView.getColumns().addAll(nameCol,groupCol,sessionCol,payCol);

    }

    @FXML
    void onAddClick() throws SQLException {
        Student s;
        Group g = Group.getInstance(Groups.getSelectionModel().getSelectedItem());
        s = new Student(id.getText(),name.getText(),g);
        Student.students.add(s);
        DataBase db = DataBase.CreateDataBase();
        db.Add(s,"student");
    }

    @FXML
    void onCaptureClick(){
        File f = new File("Assets",Groups.getSelectionModel().getSelectedItem());
        f.mkdirs();

    }
}
