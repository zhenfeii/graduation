package com.gdky.graduation.wx.dao.impl;

import com.gdky.graduation.wx.dao.UserDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public void alertAddress(Map<String, Object> map) {
        String openid = (String)map.get("openid");
        String username = (String) map.get("username");
        String phone = (String) map.get("phone");
        String address = (String) map.get("address");
        String addressDetail = (String) map.get("addressDetail");

        String sql = "UPDATE user_address SET USERNAME=?,PHONE=?,ADDRESS=?,ADDRESS_DETAIL=? WHERE OPENID=?";
        jdbcTemplate.update(sql,new Object[]{username,phone,address,addressDetail,openid});
    }

    @Override
    public String checkUser(String openid) {
        final String[] userAlreadyExist = {null};
        String sql = "select count(OPENID) count FROM user_address where OPENID=?";
        jdbcTemplate.query(sql, new Object[]{openid},new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                if(rs.getInt("count") == 0){
                    userAlreadyExist[0] = "false";
                }else{
                    userAlreadyExist[0] = "true";
                }
            }
        });
        return userAlreadyExist[0];
    }

    @Override
    public void addAddress(Map<String, Object> map) {
        String openid = (String)map.get("openid");
        String username = (String) map.get("username");
        String phone = (String) map.get("phone");
        String address = (String) map.get("address");
        String addressDetail = (String) map.get("addressDetail");

        String sql = "INSERT INTO user_address(OPENID,USERNAME,PHONE,ADDRESS,ADDRESS_DETAIL) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{openid,username,phone,address,addressDetail});
    }
}
