package com.example.systemforroboticsacademy;

public class WorkerFactory {

    public Worker CreateWorker(String type,String id,String name,String phone,String nid,int pay){
        Worker w;
        if(type.equals("Assistance")){
            w = new Assistance(id,name,phone,nid);
        }else if(type.equals("Instructor")){
            w = new Instructor(id,name,phone,nid,pay);

        }else {
            w = new Secertary(id,name,phone,nid,pay);
        }
        return w;
    }

    public Worker CreateWorker(String type,String id,String name,String phone,String nid,int pay,int duration){
        Worker w;
        if(type.equals("Assistance")){
            w = new Assistance(id,name,phone,nid);
        }else if(type.equals("Instructor")){
            w = new Instructor(id,name,phone,nid,pay,duration);

        }else {
            w = new Secertary(id,name,phone,nid,pay);
        }
        return w;
    }


}
