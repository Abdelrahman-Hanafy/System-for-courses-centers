package com.example.systemforroboticsacademy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Worker {
    private String ID, Phone,Name;
    private  String NID;

    public static ArrayList<Worker> workers = new ArrayList<>();

    public Worker (String id,String name,String phone,String nid){
        ID = id;
        Phone = phone;
        Name = name;
        NID = nid;
    }

    public static void Generate() throws SQLException {
        DataBase db = DataBase.CreateDataBase();
        ResultSet rs = db.Load("worker");
        WorkerFactory wf = new WorkerFactory();
        while(rs != null && rs.next()){
            String[] data = rs.getString("ID").split("-");
            Worker w = wf.CreateWorker(data[1],data[0],rs.getString("Name"),rs.getString("Phone"),
                    rs.getString("NID"),Integer.parseInt(rs.getString("Payment")),
                            Integer.parseInt(rs.getString("Duration")));
            workers.add(w);
        }

    }

    public abstract int getPaid();
    public abstract void worked(int duration);

    public String getID() {
        return ID;
    }

    public String getPhone() {
        return Phone;
    }

    public String getName() {
        return Name;
    }

    public String getNID() {
        return NID;
    }

    public static Worker getInstance(String id) throws SQLException {return null;}

    @Override
    public String toString() {
        return getID() ;
    }
}
