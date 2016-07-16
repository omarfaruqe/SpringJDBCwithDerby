/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author faruqe
 */
public class SimpleJdbcDaoImpl extends NamedParameterJdbcDaoSupport {

    public int getCircleCount() {
        return this.getJdbcTemplate().queryForInt("SELECT COUNT(*) FROM circle");
    }

}
