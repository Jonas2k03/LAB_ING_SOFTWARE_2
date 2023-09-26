package com.mycompany.labsoftii_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Juan Carlos Fernandez Cuetia (jcfernandezc@unicauca.edu.co)
 * @author Jonathan Felipe Hurtado Díaz (jfhurtadod@unicauca.edu.co)
 */
public class DBConnection {

    public static DBConnection instance = new DBConnection();
    private Connection conn;
    private String nameDB;

    

    private DBConnection() {

    }

    public static synchronized  DBConnection getDBInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }


    
    public void connect() {
        // SQLite connection string
        //String url = "jdbc:sqlite:./myDatabase.db"; //Para Linux/Mac
        //String url = "jdbc:sqlite:C:/sqlite/"+nameDB+".db"; //Para Windows
        String url = "jdbc:sqlite:" + nameDB + ".db";

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public String getNameDB() {
        return nameDB;
    }

    public void setNameDB(String nameDB) {
        this.nameDB = nameDB;
    }

}
