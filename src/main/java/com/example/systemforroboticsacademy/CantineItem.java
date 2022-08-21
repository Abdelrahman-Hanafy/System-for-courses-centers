package com.example.systemforroboticsacademy;

import java.sql.SQLException;

public class CantineItem implements Ibuyable{
    private String ID,type;
    private int price,quantity;

    public CantineItem(String id,String type,int price){
        quantity=0;
        ID = id;
        this.price = price;
        this.type = type;
    }

    public CantineItem(String id,String type,int price,int qnt){
        quantity=qnt;
        ID = id;
        this.price = price;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getID() {
        return ID;
    }

    @Override
    public int count() {
        return quantity;
    }

    @Override
    public void inc(int qnt) {
        quantity += qnt;
    }

    @Override
    public int buy() throws SQLException {
        quantity--;
        if (quantity == 0) Cantine.CreateCantine().items.remove(this);
        return price;
    }

    @Override
    public int buy(int qnt) throws SQLException {
        quantity -= qnt;
        if(quantity < 0){
            quantity += qnt;
            return 0;
        }
        if (quantity == 0) Cantine.CreateCantine().items.remove(this);
        return price*qnt;
    }

    @Override
    public String toString() {
        return "\""+ ID     + "\","+
               "\""+ type   + "\","+
                     price  + ","  +
                     quantity      ;
    }
}
