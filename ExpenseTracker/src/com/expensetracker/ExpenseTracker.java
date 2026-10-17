/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.expensetracker;

import com.expensetracker.model.dao.DaoFactory;
import com.expensetracker.model.dao.ExpenseCategoryDao;
import com.expensetracker.model.entities.User;
import com.expensetracker.model.entities.ExpenseCategory;
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
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            ExpenseCategoryDao expCatDao = DaoFactory.createExpenseCategoryDao(conn);
            
            ExpenseCategory expCat = expCatDao.getExpenseCategoryById("MISC");
            
            System.out.println(expCat);
            
            // -- HANDS-ON Main priority
            // TODO:
            // 1.) User java object or entity
            // 2.) UserDao
            // 3.) UserDaoImpl -- include in DaoFactory
            // 4.) Perform tests in main class -- this class
            
            
            // User acceptance Tests 
            // 1.) Create user
            // 2.) Get user
            // 3.) Update user password
            // 4.) Set to Null user object then get user again to check updated password
            // 5.) Delete user either id or username
            
            
            
            // Extra TODO -- low priority
            // 1.) Expense java object or entity
            // 2.) ExpenseDao
            // 3.) ExpenseDaoImpl -- include in DaoFactory
            // 4.) Perform tests in main class -- this class
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
    
    public static void createUser(Connection conn, String uqUsername, String password) {
        StringBuilder query = new StringBuilder();
        query.append(" INSERT INTO user (username, password) ");
        query.append(" VALUES (?, ?)                         ");
        
        System.out.println("--- createUser");
        
        try (PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
            pstmt.setString(1, uqUsername);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        }
        catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        }
        catch (NullPointerException nullE) {
            System.out.println(nullE.getMessage());
        }
    }
    
    public static User getUserById(Connection conn, int id) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT  user_id, username ");
        query.append(" FROM    user              ");
        query.append(" WHERE   user_id = ?       ");
        
        System.out.println("--- getUserById");
        
        User user = null;
        
        try (PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                }
            }
        }
        catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        }
        catch (NullPointerException nullE) {
            System.out.println(nullE.getMessage());
        }
        
        return user;
    }
    
    public static User getUserByUsername(Connection conn, String uqUsername) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT  user_id, username ");
        query.append(" FROM    user              ");
        query.append(" WHERE   username = ?      ");
        
        System.out.println("--- getUserByUsername");
        
        User user = null;
        
        try (PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
            pstmt.setString(1, uqUsername);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                }
            }
        }
        catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        }
        catch (NullPointerException nullE) {
            System.out.println(nullE.getMessage());
        }
        
        return user;
    }
    
    public static void updateUser(Connection conn, User user, String newPassword) {
        StringBuilder query = new StringBuilder();
        query.append(" UPDATE  user         ");
        query.append(" SET     password = ? ");
        query.append(" WHERE   user_id = ?  ");
        
        System.out.println("--- updateUser");
        
        try (PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
            pstmt.setString(1, newPassword);
            pstmt.setInt(2, user.getId());
            pstmt.executeUpdate();
        }
        catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        }
        catch (NullPointerException nullE) {
            System.out.println(nullE.getMessage());
        }
    }
    
    public static void deleteUserById(Connection conn, int id) {
        StringBuilder query = new StringBuilder();
        query.append(" DELETE FROM user  ");
        query.append(" WHERE user_id = ? ");
        
        System.out.println("--- deleteUserById");
        
        try (PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
        catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        }
        catch (NullPointerException nullE) {
            System.out.println(nullE.getMessage());
        }
    }
    
    public static void deleteUserByUsername(Connection conn, String uqUsername) {
        StringBuilder query = new StringBuilder();
        query.append(" DELETE FROM user  ");
        query.append(" WHERE username = ? ");
        
        System.out.println("--- deleteUserByUsername");
        
        try (PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
            pstmt.setString(1, uqUsername);
            pstmt.executeUpdate();
        }
        catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        }
        catch (NullPointerException nullE) {
            System.out.println(nullE.getMessage());
        }
    }
    
}
