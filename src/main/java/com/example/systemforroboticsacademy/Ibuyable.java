package com.example.systemforroboticsacademy;

import java.sql.SQLException;

public interface Ibuyable {
    int count();
    void inc(int qnt);
    int buy() throws SQLException;
    int buy(int qnt) throws SQLException;
}
