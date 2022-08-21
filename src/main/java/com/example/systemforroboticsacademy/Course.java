package com.example.systemforroboticsacademy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Course {

    private String ID,Name,Info;

    public static ArrayList<Course> courses = new ArrayList<>();


    public Course(String id, String name, String info){
        ID = id;
        Name = name;
        Info = info;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getInfo() {
        return Info;
    }

    public static void Generate() throws SQLException {
        DataBase db = DataBase.CreateDataBase();
        ResultSet res = db.Load("course");
        while (res !=null && res.next()){
            courses.add(new Course(res.getString("ID"),res.getString("Name"),res.getString("Info")));
        }
    }

    public static Course getInstance(String id){
        for (Course c:courses) {
            //System.out.println(c.ID);
            if (c.ID.equals(id)) return c;
        }

        return null;
    }

    @Override
    public String toString() {
        return
                "\""+ ID   + "\","+
                "\""+ Name + "\","+
                "\""+ Info + "\"" ;
    }
}
