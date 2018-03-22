package com.gdky.graduation.pc.PcDao.impl;

import com.gdky.graduation.pc.PcDao.CustomUserDao;
import com.gdky.graduation.entity.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2018/3/20 0020.
 */
@Repository
public class CustomUserDaoImpl implements CustomUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public CustomUserDetails loadUserByUsername(String username) {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        String sql = "select * from seller_user WHERE USERNAME=?;";

        jdbcTemplate.query(sql, new Object[]{username}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                customUserDetails.setUsername(rs.getString("username"));
                customUserDetails.setPassword(rs.getString("password"));
            }
        });
        return customUserDetails;
    }
}
