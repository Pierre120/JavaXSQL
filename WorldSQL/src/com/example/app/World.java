/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.example.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pierre
 */
public class World {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/world";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";
    
    private static Connection conn = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("Testing DB Connection...");
            connectDB();
            conn.close();
            System.out.println("Closed DB Connection");
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void connectDB() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connected to World DB!");
        } catch (SQLException ex) {
            System.getLogger(World.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
}
