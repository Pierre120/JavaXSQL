/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.expensetracker.model.dao;
import com.expensetracker.model.entities.ExpenseCategory;

/**
 *
 * @author pierre
 */
public interface ExpenseCategoryDao {
    
    ExpenseCategory getExpenseCategoryById(String id);
    
}
