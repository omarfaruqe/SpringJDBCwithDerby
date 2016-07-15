/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc;

import com.mc.dao.JdbcDaoImpl;
import com.mc.model.Circle;
import java.sql.SQLException;

/**
 *
 * @author faruqe
 */
public class SpringJdbcDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        // TODO code application logic here

        Circle circle = new JdbcDaoImpl().getCircle(1);
        System.out.println(circle.getName());

    }

}
