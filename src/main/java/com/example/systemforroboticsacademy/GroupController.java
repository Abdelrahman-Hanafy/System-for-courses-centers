package com.example.systemforroboticsacademy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class GroupController extends StageController{

    @FXML
    TextField inst;

    @FXML
    TextField id;

    @FXML
    TextField Asis;

    @FXML
    ComboBox<String> Day;

    @FXML
    ComboBox<String> Slot;

    @FXML
    TextField course;

    @FXML
    TableView tableView;
    ObservableList<Group> g = FXCollections.observableArrayList(Group.groups);



    ObservableList<String> days = FXCollections.observableArrayList("Fri","Sat","Mon","Thu");
    ObservableList<String> slots = FXCollections.observableArrayList("9 - 11","11 - 1","1 - 3","3 - 5","5 - 7","7 - 9");

    @FXML
    public void initialize(){
        Day.setItems(days);
        Slot.setItems(slots);

        TableColumn idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<Worker,String>("ID"));
        TableColumn InstructorCol = new TableColumn<>("Instructor");
        InstructorCol.setCellValueFactory(new PropertyValueFactory<Worker,String>("Instructor"));
        TableColumn AssistanceCol = new TableColumn<>("Assistance");
        AssistanceCol.setCellValueFactory(new PropertyValueFactory<Worker,String>("Assistance"));
        TableColumn CourseCol = new TableColumn<>("Course");
        CourseCol.setCellValueFactory(new PropertyValueFactory<Worker,String>("Course"));
        TableColumn DayCol = new TableColumn<>("Day");
        DayCol.setCellValueFactory(new PropertyValueFactory<Worker,String>("Day"));
        TableColumn SlotCol = new TableColumn<>("Slot");
        SlotCol.setCellValueFactory(new PropertyValueFactory<Worker,Integer>("Slot"));


        tableView.setItems(g);
        tableView.getColumns().addAll(idCol,InstructorCol,AssistanceCol,CourseCol,DayCol,SlotCol);
    }

    @FXML
    void onAddClick() throws SQLException {
        Group g;
        Instructor i = Instructor.getInstance(inst.getText());
        Assistance a = Assistance.getInstance(Asis.getText());
        Course c = Course.getInstance(course.getText());
        System.out.println(i.getName());
        g = new Group(id.getText(),c,i,a,Day.getSelectionModel().getSelectedItem(),Slot.getSelectionModel().getSelectedIndex());
        DataBase db = DataBase.CreateDataBase();
        db.Add(g,"groups");
        Group.groups.add(g);
    }

}
