package com.example.systemforroboticsacademy;

import javafx.scene.image.Image;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Student {

    private Group G;
    private Image image;
    private String ID, Name;
    private boolean isPay;
    private int Session,Month;

    public static ArrayList<Student> students = new ArrayList<>();

    public Student(String id, String name, Group g){
        Month =0;
        Session =0;
        isPay =false;
        image = null;
        ID = id;
        Name = name;
        G = g;
    }

    public Student(String id, String name, Group g,String Pay,int month,int session,String img){
        Month =month;
        Session =session;
        isPay =(Pay.equals("False")?false:true);
        image = (img!=null?new Image(img):null);
        ID = id;
        Name = name;
        G = g;
    }

    public void takeAttendance() throws SQLException {
        Session --;
        if(Session ==0) {isPay =false;}
        DataBase db = DataBase.CreateDataBase();
        db.Update(ID,"Student","Pay",String.valueOf(isPay));
        db.Update(ID,"Student","Session",Session);

    }

    public void pay() throws SQLException {
        isPay = true;
        Session += 4;
        Month ++;
        DataBase db = DataBase.CreateDataBase();
        db.Update(ID,"Student","Pay",String.valueOf(isPay));
        db.Update(ID,"Student","Session",Session);
        db.Update(ID,"Student","Month",Month);

    }

    public String getID() {
        return ID;
    }
    public String getGroup(){return  G.getID();}

    public String getName() {
        return Name;
    }

    public String getPay() {
        return String.valueOf(isPay);
    }

    public int getSession() {
        return Session;
    }

    public static void Generate() throws SQLException {
        DataBase db = DataBase.CreateDataBase();
        ResultSet res = db.Load("Student");
        while (res !=null && res.next()){
            //System.out.println(res.getString("Group"));
            Group g = Group.getInstance(res.getString("Group"));
            Student.students.add(new Student(res.getString("ID"), res.getString("Name"),g,
                    res.getString("Pay"),Integer.parseInt(res.getString("Month")),
                    Integer.parseInt(res.getString("Session")), res.getString("Image")));
        }
    }

    public static Student getInstance(String id){
        for (Student s:students) {
            if (s.ID.equals(id)) return s;
        }

        throw new NullPointerException();
    }

    @Override
    public String toString() {
        String img;
        if (image != null) img = "\""+image.getUrl()+"\"";
        else img = "NULL";
        return
                "\""+ ID   + "\","+
                "\""+ Name + "\","+
                Session + "," +
                Month   + ","+
                img + ","+
                "\""+ isPay + "\"," +
                "\""+ G.getID() + "\"" ;
    }

}
