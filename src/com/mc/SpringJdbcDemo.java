/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc;

import com.mc.dao.JdbcDaoImpl;
import com.mc.model.Circle;
import java.sql.SQLException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author faruqe
 */
public class SpringJdbcDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
//        Circle circle = new JdbcDaoImpl().getCircle(1);
//        System.out.println(circle.getName());
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        JdbcDaoImpl dao = ctx.getBean("jdbcDaoImpl", JdbcDaoImpl.class);

        
//        System.out.println(dao.getCircle(1).getName());        
        System.out.println(dao.getCircleCount()); // will return count of circle
        //System.out.println(dao.getCircleName(3)); //Will return circle name        
        //System.out.println(dao.getCircleForId(3).getName()); // will return circle name for id 1
        //System.out.println(dao.getAllCircles().size());
        //dao.insertCircle(new Circle(3,"Third Circle"));
        //dao.insertCircleUsingNamedParameter(new Circle(8,"eight Circle"));
    }

}
