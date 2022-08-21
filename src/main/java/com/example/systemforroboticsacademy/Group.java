package com.example.systemforroboticsacademy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Group {

    private Course c;
    private Instructor i;
    private Assistance a;
    private String ID,Day;
    private int Slot;

    public static ArrayList<Group> groups = new ArrayList<>();


    public Group(String id, Course c, Instructor i, Assistance a, String day, int slot){
        ID = id;
        Day =day;
        Slot =slot;
        this.c =c;
        this.i =i;
        this.a =a;
    }

    public Group(String id, Course c, Instructor i, String day, int slot){
        ID = id;
        Day =day;
        Slot =slot;
        this.c =c;
        this.i =i;
    }

    public String getID() {
        return ID;
    }

    public String getDay() {
        return Day;
    }

    public String getInstructor(){return i.getID();}
    public String getCourse(){return c.getID();}
    public String getAssistance(){return (a!=null?a.getID():null);}

    public int getSlot() {
        return Slot;
    }


    public void setA(Assistance a) {
        this.a = a;
    }

    public void takeAttandance() throws SQLException {
        DataBase db = DataBase.CreateDataBase();

        i.worked(2);
        db.Update(i.getID()+"-Instructor","worker","Duration",i.getHour());

        if(a!=null) {
            a.worked(2);
            db.Update(a.getID()+"-Assistance","worker","Duration",a.getHour());
        }

        for(Student s:Student.students){
            //System.out.println(s.getID());
            if(s.getGroup().equals(ID)) s.takeAttendance();
        }
    }

    public static void Generate() throws SQLException {
        DataBase db = DataBase.CreateDataBase();
        ResultSet res = db.Load("groups");
        while (res !=null && res.next()){
                Course c = Course.getInstance(res.getString("course"));
                Instructor i = Instructor.getInstance(res.getString("insructor"));
                Group g = new Group(res.getString("ID") ,c,i,res.getString("Day"),
                        Integer.parseInt(res.getString("Slot")));

            if(res.getString("assistant")!=null) {
                //System.out.println(res.getString("assistant"));
                Assistance a = Assistance.getInstance(res.getString("assistant"));
                g.setA(a);
            }
            groups.add(g);
        }
    }

    public static Group getInstance(String id){
        for (Group g:groups) {
            //System.out.println(g.getID());
            if (g.ID.equals(id)) return g;
        }

        return null;
    }

    @Override
    public String toString() {
        String asis;
        if(a != null) asis="\""+a.getID()+"\"";
        else asis = "NULL";
        return
                "\""+ ID   + "\","+
                        "\""+ i.getID() + "\","+
                         asis + "," +
                        "\""+ c.getID()   + "\","+
                        "\""+ Day + "\","+
                        Slot  ;
    }

}
