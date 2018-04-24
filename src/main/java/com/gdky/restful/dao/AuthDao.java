package com.gdky.restful.dao;

import com.gdky.restful.entity.Role;
import com.gdky.restful.entity.User;
import com.gdky.restful.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2018/4/8 0008.
 */
@Repository
@Transactional
public class AuthDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User getUser(String loginName) {
        String sql = "select * from seller_user WHERE LOGINNAME=?;";
        List<User> users = jdbcTemplate.query(sql, new Object[]{loginName}, new UserRowMapper());
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    public List<Role> getRolesByUser(String username) {

        return null;
    }

    public void insertDlxx(String random, CustomUserDetails userDetails) {
        String sql = "update seller_user set dlxx = ? where ID= ? ";
        jdbcTemplate.update(sql, new Object[]{random, userDetails.getId()});
    }

    //第一次使用内部类
    private class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("ID"));
            user.setSeller_id(rs.getInt("SELLER_ID"));
            user.setLoginName(rs.getString("LOGINNAME"));
            user.setUserName(rs.getString("USERNAME"));
            user.setPhone(rs.getString("PHONE"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setPhoto(rs.getString("AVATAR"));
            user.setDlxx(rs.getString("DLXX"));
            return user;
        }
    }
}
