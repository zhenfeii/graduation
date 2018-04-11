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

    public User getUser(String username) {
        String sql = "select * from user WHERE LOGIN_NAME=?;";
        List<User> users = jdbcTemplate.query(sql, new Object[]{username}, new UserRowMapper());
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    public List<Role> getRolesByUser(String username) {
        String sql = "select c.* from user a,user_role b,role c where a.id_=b.user_id and b.role_id=c.id_ and a.login_name=?";
        List<Role> roles = jdbcTemplate.query(sql, new Object[]{username}, new RoleRowMapper());
        return roles;
    }

    public void insertDlxx(String random, CustomUserDetails userDetails) {
        String sql = "update user set dlxx = ? where id_ = ? ";
        jdbcTemplate.update(sql, new Object[]{random, userDetails.getId_()});
    }

    private class RoleRowMapper implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet rs, int i) throws SQLException {
            Role role = new Role();
            role.setId_(rs.getInt("id_"));
            role.setMs(rs.getString("ms"));
            role.setRoleName(rs.getString("role_Name"));
            return role;
        }
    }

    //第一次使用内部类
    private class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setId_(rs.getInt("id_"));
            user.setLoginName(rs.getString("login_Name"));
            user.setDeptid(rs.getInt("deptid"));
            user.setPhone(rs.getString("phone"));
            user.setRzsj(rs.getDate("rzsj"));
            user.setPwd(rs.getString("pwd"));
            user.setUserName(rs.getString("user_Name"));
            user.setPhoto(rs.getString("photo"));
            user.setBirthday(rs.getDate("birthday"));
            return user;
        }
    }
}
