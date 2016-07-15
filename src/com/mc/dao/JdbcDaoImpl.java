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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author faruqe
 */
@Component
public class JdbcDaoImpl {

    
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate; // = new JdbcTemplate();

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public int getCircleCount(){                
        return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM circle");      
    }    
    
    public String getCircleName(int circleId){
        return jdbcTemplate.queryForObject("SELECT NAME FROM circle WHERE ID=?", new Object[] {circleId}, String.class);             
    }
    
    public Circle getCircleForId(int circleId){
        String sql = "SELECT * FROM circle WHERE ID=?";        
        return jdbcTemplate.queryForObject(sql, new Object[]{circleId},new CircleMapper());
    }
    
    private static final class CircleMapper implements RowMapper<Circle>{
        @Override
        public Circle mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            Circle circle = new Circle();
            circle.setId(resultSet.getInt("ID"));
            circle.setName(resultSet.getString("NAME"));
            return circle;
        }
        
    }
}
