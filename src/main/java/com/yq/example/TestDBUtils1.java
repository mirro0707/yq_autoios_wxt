package com.yq.example;

import java.sql.Connection;
import java.sql.SQLException;

import com.wxt.util.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.testng.annotations.Test;


/**
 * 测试DBUtils工具类的增删改操作
 *
 * 没有给connection，但是给了一个池子，自动拿一个连接，因为不需要开启事务。
 * 事务需要同一个connection
 *
 * 为什么没有归还池子？自动归还？？？？
 *
 */
public class TestDBUtils1 {

	/**
	 * 添加所有用户方法
	 */
	@Test
	public void testAddUser() {
		Connection conn = null;
		try {
			// 1.创建核心类QueryRunner
			QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());//无法解决事务问题（需要同一个connection）
//			QueryRunner qr = new QueryRunner();//不给池子，给个连接也行，不然没法找到数据库
			// 2.编写SQL语句
			String sql = "insert into tbl_user values(null,?,?)";
			// 3.为站位符设置值
//			Object[] params = { "余淮", "耿耿" };
			// 4.执行添加操作
//			int rows = qr.update(sql, params);
			int rows = qr.update(sql, new Object[]{"余淮", "耿耿"});
			if (rows > 0) {
				System.out.println("添加成功!");
			} else {
				System.out.println("添加失败!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据id修改用户方法
	 * 
	 */
	@Test
	public void testUpdateUserById() {
		try {
			// 1.创建核心类QueryRunner
			QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
			// 2.编写SQL语句
			String sql = "update tbl_user set upassword=? where uid=?";
			// 3.为站位符设置值
			Object[] params = { "xxx", 21 };
			// 4.执行添加操作
			int rows = qr.update(sql, params);
			if (rows > 0) {
				System.out.println("修改成功!");
			} else {
				System.out.println("修改失败!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据id删除用户方法
	 */
	@Test
	public void testDeleteUserById() {
		try {
			// 1.创建核心类QueryRunner
			QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
			// 2.编写SQL语句
			String sql = "delete from tbl_user where uid=?";
			// 3.为站位符设置值
			Object[] params = {19};
			// 4.执行添加操作
			int rows = qr.update(sql, params);
			if (rows > 0) {
				System.out.println("删除成功!");
			} else {
				System.out.println("删除失败!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
