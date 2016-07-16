/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.dao;

import com.mc.model.Circle;
import java.sql.*;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Component;

/**
 *
 * @author faruqe
 */
@Component
public class JdbcDaoImpl {

    
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate; // = new JdbcTemplate();
    
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
//    public void createTriangleTable(){
//        String sql = "CREATE TABLE TRIANGLE(ID INTEGER, NAME VARCHAR(50))";
//        jdbcTemplate.execute(sql);
//    }
//    public void insertCircle(Circle circle){
//        String sql = "INSERT INTO CIRCLE(ID, NAME) VALUES( ?, ?)";
//        jdbcTemplate.update(sql, new Object[]{circle.getId(),circle.getName()});
//    }
    
    //Uisng Named Parameter
    public void insertCircleUsingNamedParameter(Circle circle){
        String sql = "INSERT INTO CIRCLE(ID, NAME) VALUES( :id, :name)";      
        SqlParameterSource namedParameters = new MapSqlParameterSource("id",circle.getId()).addValue("name", circle.getName());
        namedParameterJdbcTemplate.update(sql, namedParameters);        
    }
    
    public int getCircleCount(){                
        return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM circle");      
    }    
    
    public String getCircleName(int circleId){
        return jdbcTemplate.queryForObject("SELECT NAME FROM circle WHERE ID=?", new Object[] {circleId}, String.class);             
    }
    
    public List<Circle> getAllCircles(){
        String sql = "SELECT * FROM circle";
        return jdbcTemplate.query(sql,new CircleMapper());
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
