package com.example.systemforroboticsacademy;

public class Secertary extends Worker{

    private int day, salary;

    public Secertary(String id,String name,String phone,String nid,int s){
        super(id,name,phone,nid);
        day=0;
        salary=s;
    }

    @Override
    public int getPaid() {
        int payment  = salary - (20 - day)*50;
        day = 0;
        return payment;
    }

    public int getDuration(){return day;}

    @Override
    public void worked(int duration) {
        day += duration;
    }

    @Override
    public String toString() {
        return
                "\""+ getID() + "-Secertary"   + "\","+
                        "\""+ getName() + "\","+
                        "\""+ getPhone()+ "\","+
                        "\""+ getNID()  + "\","+
                        day + ","  +
                        salary  ;
    }
}
