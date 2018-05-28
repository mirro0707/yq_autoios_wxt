package com.yq.example;

import com.wxt.domain.User;
import com.wxt.util.C3P0Utils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DBUtilsTest {
    @Test
    public void testQuery() {
        //创建连接
        Connection conn = C3P0Utils.getConnection();
        //创建SQL执行工具
        QueryRunner queryRunner = new QueryRunner();
        List<User> list = null;
        try {
            //执行SQL查询，并获取结果
            list = queryRunner.query(conn, "select * from persons", new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list != null) {
            //输出查询结果
            list.forEach(System.out::println);
        }
        //关闭数据库连接
        DbUtils.closeQuietly(conn);
    }

    @Test
    public void testQuery2() {
        Connection connection = C3P0Utils.getConnection();
        QueryRunner runner = new QueryRunner();
        try {
            User user = runner.query(connection, "SELECT * FROM persons WHERE id = ?", new BeanHandler<>(User.class), 1);
            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbUtils.closeQuietly(connection);
    }
    @Test
    public void testQuery3() {
        Connection connection = C3P0Utils.getConnection();
        QueryRunner runner = new QueryRunner();
        try {
            Map<String, Object> map = runner.query(connection, "SELECT * FROM persons WHERE id = ?", new MapHandler(), 3);
            System.out.println(map.get("id") + " " + map.get("username") + "  " + map.get("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbUtils.closeQuietly(connection);
    }
    @Test
    public void testQuery4() {
        Connection connection = C3P0Utils.getConnection();
        QueryRunner runner = new QueryRunner();
        try {
            List<Map<String, Object>> list = runner.query(connection, "SELECT * FROM persons", new MapListHandler());
            for (Map<String, Object> map : list) {
                System.out.println(map.get("id") + " " + map.get("name") + "  " + map.get("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbUtils.closeQuietly(connection);
    }

    @Test
    public void testUpdate() {
        //创建连接
        Connection conn = C3P0Utils.getConnection();
        //创建SQL执行工具
        QueryRunner queryRunner = new QueryRunner();
        int rows = 0;
        try {
            //执行SQL插入
            rows = queryRunner.update(conn, "INSERT INTO persons(name, age) VALUES(?, ?)", "阡陌", 24);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("成功插入" + rows + "条数据！");
        //关闭数据库连接
        DbUtils.closeQuietly(conn);
    }

}
