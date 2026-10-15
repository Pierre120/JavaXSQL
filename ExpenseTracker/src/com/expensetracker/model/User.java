/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.expensetracker.model;

/**
 *
 * @author pierre
 */
public class User {
    
    private int id;
    private String username;
    
    public User() {}
    
    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public int getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }
    
}
