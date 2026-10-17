/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.expensetracker.model.entities;

/**
 *
 * @author pierre
 */
public class ExpenseCategory {
    
    private String categoryId;
    private String name;
    
    public ExpenseCategory() {
        super();
    }
    
    public ExpenseCategory(String categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }
    
    // Setters
    public void setId(String id) {
        this.categoryId = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    // getters
    public String getcategoryId() {
        return categoryId;
    }
    
    public String getUsername() {
        return name;
    }

    @Override
    public String toString() {
        return "ExpenseCategory [categoryId=" + categoryId + ", name=" + name + "]";
    }
    
    
}
