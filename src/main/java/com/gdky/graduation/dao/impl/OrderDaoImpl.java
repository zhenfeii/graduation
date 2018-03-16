package com.gdky.graduation.dao.impl;

import com.gdky.graduation.dao.OrderDao;
import com.gdky.graduation.utils.TimeConverterUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/12 0012.
 */
@Repository
public class OrderDaoImpl implements OrderDao {
    //设置日期格式
    final static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addOrder(Map<String, Object> params) {
        String uuid = (String) params.get("uuid");
        String username = (String) params.get("username");
        String useravatar = (String) params.get("useravatar");
        String openid = (String) params.get("openid");
        Integer sellerid = Integer.parseInt(params.get("sellerid").toString());
        String deliveryPrice = (String) params.get("deliveryPrice");
        String totalPrice = params.get("totalPrice").toString();


        //在order表新增一条数据
        //order 是系统关键字，必须要用``
        String orderSql = "INSERT INTO `order`(UUID,USERNAME,USERAVATAR,OPENID,SELLERID,DELIVERYPRICE,TOTALPRICE) " +
                "VALUES(?,?,?,?,?,?,?)";
        jdbcTemplate.update(orderSql, new Object[]{uuid, username, useravatar, openid, sellerid, deliveryPrice, totalPrice});

        //处理购物车字符串
        String cartArrayString = (String) params.get("cartArrayString");
        JSONArray jsonArray = new JSONArray(cartArrayString);
        jsonArray.get(0);

        //遍历jsonArray数组
        for (int i = 0; i < jsonArray.length(); i++) {
            Object object = jsonArray.get(i);
            object.toString();

            String foodName = (String) ((JSONObject) object).opt("name");
            String num = (String) ((JSONObject) object).opt("num").toString();
            String price = (String) ((JSONObject) object).opt("price").toString();

            //把订单食物详情新增到order_food数据表
            jdbcTemplate.update("INSERT INTO `order_food`(UUID, FOODNAME, FOODNUM, PRICE) VALUES(?,?,?,?)",
                    new Object[]{uuid, foodName, num, price});
        }
    }

    @Override
    public List<Map<String, Object>> getOrderBySellerId(Integer sellerId) {
        List<Map<String, Object>> orderList = new ArrayList<>();
        String sql = "SELECT * FROM `order` WHERE SELLERID=?";
        jdbcTemplate.query(sql, new Object[]{sellerId}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Map<String, Object> map = new HashMap<>();

                //灵感： jdbcTemplate.queryForList 嫌弃它取字段是数据库的大写，
                // 但mysql在查询的时候就支持改变列名的功能
                map.put("uuid", rs.getString("UUID"));
                map.put("username", rs.getString("USERNAME"));
                map.put("totalprice", rs.getString("TOTALPRICE"));
                map.put("createtime", rs.getString("CREATE_TIME"));
                orderList.add(map);
            }
        });
        return orderList;
    }

    @Override
    public void deleteOrderById(Integer orderId) {
        String sql = "DELETE FROM `order` WHERE id=?";
        jdbcTemplate.update(sql, new Object[]{orderId});
    }

    @Override
    public List<Map<String, Object>> getOrderFoodByUuid(String uuid) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        String sql = "SELECT FOODNAME foodname,FOODNUM foodnum,PRICE price from order_food where UUID=?";
        resultList = jdbcTemplate.queryForList(sql, new Object[]{uuid});
        return resultList;
    }

    @Override
    public void editOrder(Map<String, Object> map) {
        String sql = "UPDATE order_food SET FOODNUM=?,PRICE=? WHERE UUID=? AND FOODNAME=?";
        String uuid = map.get("uuid").toString();

        //解析还原前台传来的food数组字符串
        String foodArrayString = map.get("foodArrayString").toString();
        JSONArray foodjsonArray = new JSONArray(foodArrayString);

        float totalPrice = 0;
        //遍历josn数组
        for (int j = 0; j < foodjsonArray.length(); j++) {
            JSONObject foodObject = foodjsonArray.getJSONObject(j);
            String foodname = foodObject.opt("foodname").toString();
            String foodnum = foodObject.opt("foodnum").toString();
            String price = foodObject.opt("price").toString();
            //计算总价钱
            // string 转 float
            totalPrice = totalPrice + Float.parseFloat(price) * Float.parseFloat(foodnum);

            jdbcTemplate.update(sql, new Object[]{foodnum, price, uuid, foodname});
        }
        //把数据表的订单总价格也要修改
        String totalPriceSql = "UPDATE `order` SET TOTALPRICE=? WHERE uuid=?";

        //float 转 string
        jdbcTemplate.update(totalPriceSql, new Object[]{String.valueOf(totalPrice), uuid});
    }

    @Override
    public List<Map<String, Object>> getOrderByCondition(Map<String, Object> params) {
        String initSql = "SELECT UUID uuid,USERNAME username,TOTALPRICE totalprice,CREATE_TIME createtime " +
                "FROM `order` " +
                "WHERE SELLERID=1";
        StringBuffer sql = new StringBuffer(initSql);
        List<Object> args = new ArrayList<Object>();
        String lastSql = rebuildSqlByCondition(sql, params,args);
        //小记，必须是args.toArray()
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(lastSql, args.toArray());
        return resultList;
    }

    @Override
    public List<Map<String, Object>> getOrderByOpenId(String opendId) {
        List<Map<String, Object>> orderList = new ArrayList<>();
        String sql = "SELECT UUID uuid,SELLERID sellerid,CREATE_TIME createtime,TOTALPRICE totalprice FROM `order` WHERE OPENID=?";
        jdbcTemplate.query(sql, new Object[]{opendId}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {

                //自封装数据集
                Map<String,Object> map = new HashMap<>();
                map.put("uuid",rs.getString("uuid"));
                map.put("createtime",df.format(rs.getTimestamp("createtime")));
                map.put("totalprice",rs.getString("totalprice"));

                Integer sellerId = rs.getInt("sellerid");
                final String[] name = {null};
                //根据sellerId 找出 商家名称
                jdbcTemplate.query("SELECT name from seller WHERE id=?", new Object[]{sellerId}, new RowCallbackHandler() {
                    @Override
                    public void processRow(ResultSet rs) throws SQLException {
                        name[0] = rs.getString("name");
                    }
                });
                map.put("name",name[0]);
                orderList.add(map);
            }
        });
        return orderList;
    }







    private String rebuildSqlByCondition(StringBuffer sql, Map<String, Object> params,List<Object> args) {
        //根据查询条件，变化sql
        String uuid = params.get("uuid").toString();
        String username = params.get("username").toString();


        String timeSUIC = params.get("timeS").toString();
        String timeEUTC = params.get("timeE").toString();
        //将UTC时间转北京时间
        String timeS = TimeConverterUtil.utcTolocal(timeSUIC);
        String timeE = TimeConverterUtil.utcTolocal(timeEUTC);

        if (params.size() != 0) {
            if (uuid != null && !"".equals(uuid)) {
                sql.append(" and UUID like ? ");
                args.add("%"+uuid+"%");
            }
        }
        if (params.size() != 0) {
            if (username != null && !"".equals(username)) {
                sql.append(" and USERNAME like ? ");
                args.add("%" + username + "%");
            }
        }
        if (timeS != null) {
            sql.append(" and CREATE_TIME>=? ");
            args.add(timeS);
        }
        if (timeE != null) {
            sql.append(" and CREATE_TIME>=? ");
            args.add(timeE);
        }
        return sql.toString();
    }
}
