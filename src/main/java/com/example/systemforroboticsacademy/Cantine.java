package com.example.systemforroboticsacademy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cantine {


    public ArrayList<CantineItem> items;
    private static  Cantine ct=null;

    private Cantine() throws SQLException {
        items = new ArrayList<>();
        Generate();
    }

    private void Generate() throws SQLException {
        DataBase db = DataBase.CreateDataBase();
        ResultSet rs = db.Load("cantine");
        while(rs != null && rs.next()){
            items.add(new CantineItem(rs.getString("ID"), rs.getString("Type"),
                    Integer.parseInt(rs.getString("Price")),
                    Integer.parseInt(rs.getString("Quantity"))));
        }
    }

    public void Updateitems(CantineItem ci){
        items.add(ci);
    }

    public CantineItem getInstance(String id){
        for (CantineItem i:items) {
            if (i.getID().equals(id)) return i;
        }

        return null;
    }

    public static Cantine CreateCantine() throws SQLException {
        if(ct==null){
            ct = new Cantine();
        }
        return ct;
    }

}
