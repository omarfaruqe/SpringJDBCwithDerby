/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.dao;

import com.mc.model.Circle;
import java.sql.*;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author faruqe
 */
@Component
public class JdbcDaoImpl {

    @Autowired
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public Circle getCircle(int circleId) {
        Connection conn = null;

        try {
//            String driver = "org.apache.derby.jdbc.ClientDriver";
//            Class.forName(driver).newInstance();
//            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/db");
            conn = dataSource.getConnection();
            Circle circle;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM circle where id = ?");
            ps.setInt(1, circleId);
            circle = null;
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                circle = new Circle(circleId, rs.getString("name"));
            }

            return circle;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }
}
