/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.expensetracker.model.dao;

import java.sql.Connection;
import com.expensetracker.model.dao.impl.ExpenseCategoryDaoImpl;

/**
 *
 * @author pierre
 */
public class DaoFactory {
    public static ExpenseCategoryDao createExpenseCategoryDao(Connection conn) {
        return new ExpenseCategoryDaoImpl(conn);
    }
}
