package com.wxt.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据库读取使用DBUtils工具类+C3P0
 */

public class C3P0Utils {
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource("weixintong");

	public static DataSource getDataSource() {
		return dataSource;
	}

	public static Connection getConnection() {
		try {
			return dataSource.getConnection();//从池子中获得一个connection
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
