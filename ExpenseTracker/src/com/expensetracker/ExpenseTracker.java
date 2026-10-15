/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.expensetracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pierre
 */
public class ExpenseTracker {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/et2_db";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);) {
            String username = "user0";
            String password = "user@123";
            verifyUserStmt(conn, username, password);
            System.out.println();
            verifyUserPrepStmt(conn, username, password);
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void verifyUserStmt(Connection conn, String username, String password) {
        String query = " SELECT 1 FROM user WHERE username = '" + username 
                + "' AND password = '" + password + "' ";
        
        System.out.println("--- Statement");
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            boolean isUserVerified = rs.next();
            
            if (isUserVerified) {
                System.out.println("verifyUserStmt : User is verified");
            }
            else {
                System.out.println("verifyUserStmt : Invalid username and/or password");
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        catch (NullPointerException nullE) {
            System.out.println(nullE.getMessage());
        }
    }
    
    public static void verifyUserPrepStmt(Connection conn, String username, String password) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT  1            ");
        query.append(" FROM    user         ");
        query.append(" WHERE   username = ? ");
        query.append("     AND password = ? ");
        
        System.out.println("--- PreparedStatement");
        
        try (PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
            // bind values
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                boolean isUserVerified = rs.next();
                
                if (isUserVerified) {
                    System.out.println("verifyUserPrepStmt : User is verified");
                }
                else {
                    System.out.println("verifyUserPrepStmt : Invalid username and/or password");
                }
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        catch (NullPointerException nullE) {
            System.out.println(nullE.getMessage());
        }
    }
    
}
