package com.yq.example;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.wxt.domain.User;
import com.wxt.util.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.testng.annotations.Test;


/**
 * 测试DBUtils查询操作
 *
 */
public class TestDBUtils2 {

	/*
	BeanListHandler：查询所有
	 * 查询所有用户方法
	 */
	@Test
	public void testQueryAll() {
		try {
			// 1.获取核心类queryRunner
			QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
			// 2.编写sql语句
			String sql = "select * from tbl_user";
			// 3.执行查询操作
			List<User> users = qr.query(sql, new BeanListHandler<User>(User.class));
			// 4.对结果集集合进行遍历
			for (User user : users) {
				System.out.println(user.getUsername() + " : " + user.getPassword());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	BeanHandler：查询一行
	 * 根据id查询用户方法
	 */
	@Test
	public void testQueryUserById() {
		try {
			// 1.获取核心类queryRunner
			QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
			// 2.编写sql语句
			String sql = "select * from tbl_user where uid=?";
			//3.为占位符设置值
			Object[] params = {21};
			// 4.执行查询操作
			User user = qr.query(sql, new BeanHandler<User>(User.class), params);
			System.out.println(user.getUsername() + " : " + user.getPassword());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 根据所有用户的总个数
	 */
	@Test
	public void testQueryCount() {
		try {
			// 1.获取核心类queryRunner
			QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
			// 2.编写sql语句
			String sql = "select count(*) from tbl_user";
			// 4.执行查询操作
			Long count = (Long) qr.query(sql, new ScalarHandler());
			System.out.println(count);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	MapListHandler：每一行都是按照map存储的
	 * 查询所有用户方法
	 */
	@Test
	public void testQueryAll1() {
		try {
			// 1.获取核心类queryRunner
			QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
			// 2.编写sql语句
			String sql = "select * from tbl_user";
			// 3.执行查询操作
			List<Map<String, Object>> list = qr.query(sql, new MapListHandler());
			// 4.对结果集集合进行遍历
			for (Map<String, Object> map : list) {
				System.out.println(map);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 查询所有用户方法
	 */
//	@Test
/*	public void testQueryAll2() {
		try {
			// 1.获取核心类queryRunner
			QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
			// 2.编写sql语句
			String sql = "select * from tbl_user";
			// 3.执行查询操作
			List<Object> list = qr.query(sql, new ColumnListHandler("uname"));//报错?为什么

			// 4.对结果集集合进行遍历
			for (Object object : list) {
				System.out.println(object);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}*/
}
