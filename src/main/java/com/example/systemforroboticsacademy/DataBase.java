package com.example.systemforroboticsacademy;

import javafx.scene.control.Alert;

import java.io.File;
import java.sql.*;

public class DataBase {
    private String url = "jdbc:sqlite:Data.db";
    static DataBase db=null;
    private Connection c;
    public boolean isConnected;

    private DataBase() throws SQLException {
        c =DriverManager.getConnection(url);
        isConnected = !c.isClosed();
    }

    public static DataBase CreateDataBase() throws SQLException {
        if(db != null){
            return db;
        }
        return new DataBase();
    }

    public void Add(Object o,String Table){
        String add ="Insert INTO "+Table+" VALUES ("+o.toString()+")";
        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
        try
        {
            ExcuteStat(add);
            al.setHeaderText("Done Adding");
        }catch (Exception e){
            al.setHeaderText("Faild Adding");
            al.setContentText(e.getMessage());
            System.out.println(e.getMessage());
        }finally {
            al.showAndWait();
        }
    }

    public ResultSet Load(String Table) {
        String load = "Select * From "+Table;
        ResultSet rs = ExcuteStat(load,null);
        return rs;
    }

    public ResultSet Load(String Table,String id) {
        String load = "Select * From "+Table + "\nWHERE ID Like "+id;
        ResultSet rs = ExcuteStat(load,null);
        return rs;
    }

    public void Update(String id,String Table,String col,String val) throws SQLException {
        String st = "UPDATE "+Table+"\n" +
                "SET "+ col+" = \""+val+"\"\n" +
                "WHERE\n" +
                "   ID = \""+id+"\"";

            ExcuteStat(st);
    }

    public void Update(String id,String Table,String col,int val) throws SQLException {
        String st = "UPDATE "+Table+"\n" +
                "SET "+ col+" = "+val+"\n" +
                "WHERE\n" +
                "   ID = \""+id+"\"";

        //System.out.println(st);
        ExcuteStat(st);
    }

    private void ExcuteStat(String crt) throws SQLException {
        Statement st = c.createStatement();
        st.execute(crt);
    }

    private ResultSet ExcuteStat(String crt, ResultSet res){
        try{

             Statement st = c.createStatement();
             res = st.executeQuery(crt);
             return res;

        }catch (Exception e){
            System.out.println("Can't conntect" + e.getMessage());
            return res;
        }
    }

}
