package com.example.systemforroboticsacademy;

import java.sql.SQLException;

public class Recite {

    private Student s;
    private String ID, Type, Comment;
    private int Fee;

    public Recite(Student s,String id,String type,String comment,int fee){
        this.s =s;
        ID =id;
        Type = type;
        Comment = comment;
        Fee = fee;
    }

    public Recite(Student s,String id,String type,int fee){
        this.s =s;
        ID =id;
        Type = type;
        Comment = "";
        Fee = fee;
    }

    public void pay() throws SQLException {
        DataBase db = DataBase.CreateDataBase();
        if(Type.equals("Month")) s.pay();
        db.Add(this, "reciet");
    }

    @Override
    public String toString() {
        return
                "\""+ ID   + "\","+
                        "\""+ Type   + "\","+
                        "\""+ Comment   + "\","+
                        Fee  +","+
                        "\""+ s.getID()   + "\"";
    }

}
