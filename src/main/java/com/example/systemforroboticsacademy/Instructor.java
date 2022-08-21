package com.example.systemforroboticsacademy;

import java.sql.SQLException;

public class Instructor extends Worker{

    private int hour, rate;

    public Instructor(String id,String name,String phone,String nid,int r){
        super(id,name,phone,nid);
        hour =0;
        rate =r;
    }
    public Instructor(String id,String name,String phone,String nid,int r,int h){
        super(id,name,phone,nid);
        hour =h;
        rate =r;
    }

    public int getRate() {
        return rate;
    }

    public int getHour() {
        return hour;
    }

    public int getDuration(){return hour;}

    @Override
    public int getPaid() {
        int payment = hour*rate;
        hour= 0;
        return payment;
    }

    public static Instructor getInstance(String id) throws SQLException {
        for (Worker w:workers) {
            if (w.getID().equals(id)) return (Instructor)w;
        }

        return null;
    }

    @Override
    public void worked(int duration) {
        hour += duration;
    }

    @Override
    public String toString() {
        return
                "\""+ getID() + "-Instructor"   + "\","+
                "\""+ getName() + "\","+
                "\""+ getPhone()+ "\","+
                "\""+ getNID()  + "\","+
                      getHour() + ","  +
                      getRate();
    }
}
