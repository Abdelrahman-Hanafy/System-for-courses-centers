package com.example.systemforroboticsacademy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class SessionController extends StageController{


    @FXML
    ComboBox<String> Groups;

    @FXML
    ComboBox<String> Rooms;

    @FXML
    DatePicker dt;

    ObservableList<String> groups = FXCollections.observableArrayList();
    ObservableList<String> rooms = FXCollections.observableArrayList("Room1","Room2","Room3","Private");


    @FXML
    public void initialize() throws SQLException {

        DataBase db = DataBase.CreateDataBase();
        ResultSet res = db.Load("Groups");
        while (res!=null&& res.next()){
            groups.add(res.getString("ID"));
        }

        Groups.setItems(groups);
        Rooms.setItems(rooms);
    }


    @FXML
    void onTakeClick() throws SQLException {
        Date date = Date.from(dt.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Group g = Group.getInstance(Groups.getSelectionModel().getSelectedItem());
        g.takeAttandance();
        DataBase db = DataBase.CreateDataBase();
        db.Add(new Session(date,g,Rooms.getSelectionModel().getSelectedItem()),"Sessions");
    }

    private class Session{
        Date dt;
        Group g;
        String room;
        public Session(Date d,Group g, String r){
            dt =d;
            this.g = g;
            room =r;
        }

        @Override
        public String toString() {
            return "\""+  g.getID() + "\","+
                    "\""+ dt.toString()  + "\","+
                    "\""+ room  + "\"";
        }
    }

}
