package com.gdky.graduation.dao.impl;

import com.gdky.graduation.dao.UserDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/12 0012.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<String> getopenIdList() {
        List<String> openIdList = jdbcTemplate.queryForList("SELECT OPENID FROM USER", String.class);
        return openIdList;
    }

    @Override
    public void addUser(Map<String, Object> ps) {
        String sql = "INSERT INTO USER (OPENID, USERNAME, AVATARURL, GENDER, PROVINCE, CITY, COUNTRY)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String openid = (String) ps.get("openid");
        String username = (String) ps.get("name");
        String avatarurl = (String) ps.get("avatarUrl");
        String gender = ps.get("gender").toString();
        String province = (String) ps.get("province");
        String city = (String) ps.get("city");
        String country = (String) ps.get("country");
        jdbcTemplate.update(sql, new Object[]{openid, username, avatarurl, gender, province, city, country});
    }
}
