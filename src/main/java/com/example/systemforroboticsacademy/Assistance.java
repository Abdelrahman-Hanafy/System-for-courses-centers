package com.example.systemforroboticsacademy;

public class Assistance extends Instructor{

    boolean Trained;
    public Assistance(String id,String name,String phone,String nid){
        super(id,name,phone,nid,0);
        Trained = false;
    }

    @Override
    public void worked(int duration) {
        super.worked(duration);
        if(getHour() > 100){
            Trained = true;
        }
    }

    public static Assistance getInstance(String id){
        for (Worker w:workers) {
            if (w.getID().equals(id)) return (Assistance) w;
        }

        return null;
    }


    @Override
    public String toString() {
        return
                "\""+ getID() + "-Assistance"   + "\","+
                        "\""+ getName() + "\","+
                        "\""+ getPhone()+ "\","+
                        "\""+ getNID()  + "\","+
                        getHour() + ","  +
                        getRate()  ;
    }

}
