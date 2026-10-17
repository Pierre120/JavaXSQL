/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.expensetracker.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.expensetracker.model.dao.ExpenseCategoryDao;
import com.expensetracker.model.entities.ExpenseCategory;

/**
 *
 * @author pierre
 */
public class ExpenseCategoryDaoImpl implements ExpenseCategoryDao {
    
    private Connection conn;
    
    public ExpenseCategoryDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public ExpenseCategory getExpenseCategoryById(String id) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT  *                ");
        query.append(" FROM    expense_category ");
        query.append(" WHERE   category_id = ?  ");
        
        ExpenseCategory expCat = null;
        
        try (PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
            // bind values
            pstmt.setString(1, id);
            
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    expCat = new ExpenseCategory(
                        rs.getString("category_id"),
                        rs.getString("name")
                    );
                }
            }
        
        }
        catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        }
        
        return expCat;
    }
    
}
