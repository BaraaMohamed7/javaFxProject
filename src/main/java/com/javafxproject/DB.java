package com.javafxproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class DB {

    public static Connection dbConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:BARAA/1234@localhost:1521/XE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }
    public static ObservableList<Integer> getTiIDs() throws SQLException {
        Connection con = dbConnection();
        ObservableList<Integer> tiIDs = FXCollections.observableArrayList();
        PreparedStatement pst=con.prepareStatement("select id from TICKETS");
        ResultSet res= pst.executeQuery();
        while(res.next()){
            tiIDs.add((res.getInt("id")));
        }
        return tiIDs;
    }
}
