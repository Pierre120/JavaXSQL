/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.example.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
            getCountryLanguageStmt();
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
    
    public static void getCountryLanguageStmt() {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT  *                   ");
        query.append(" FROM    CountryLanguage     ");
        query.append(" WHERE   CountryCode = 'PHL' ");
        query.append("     AND IsOfficial = 'F'    ");
        
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.createStatement();
            System.out.println("Created Statement");
            
            rs = stmt.executeQuery(query.toString());
            System.out.println("Created ResultSet");
            
            System.out.println("\nCountryCode, Language, IsOfficial, Percentage");
            
            while (rs.next()) {
                String code = rs.getString("CountryCode");
                String language = rs.getString("Language");
                String isOfficial = rs.getString("IsOfficial");
                double percentage = rs.getDouble("Percentage");
                System.out.println(code + ", " + language + ", "
                        + isOfficial + ", "
                        + percentage);
            }
            
            System.out.println();
        }
        catch(SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        }
        finally {
            // Manual cleanup or closing
            if (rs != null) {
                try {
                    rs.close();
                    System.out.println("Closed ResultSet");
                }
                catch (SQLException sqlE) {
                    System.out.println(sqlE.getMessage());
                }
            }
            
            if (stmt != null) {
                try {
                    stmt.close();
                    System.out.println("Closed Statement");
                }
                catch (SQLException sqlE) {
                    System.out.println(sqlE.getMessage());
                }
            }
        }
    }
    
}
