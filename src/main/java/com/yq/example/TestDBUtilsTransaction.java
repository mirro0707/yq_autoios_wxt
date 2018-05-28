package com.yq.example;

import java.sql.Connection;
import java.sql.SQLException;

import com.wxt.util.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;

/**
 * 只有一条sql的时候没有必要开启事务，如果出错会直接报错！
 */
public class TestDBUtilsTransaction {

	public static void main(String[] args) {
		
		Connection conn = null;
		try {
			QueryRunner runner = new QueryRunner();//不传入数据池
			
			//runner.update("update account set money=15000 where name='tom'");
			//获得一个Connection
			conn = DataSourceUtils.getConnection();
			
			//开启事务
			conn.setAutoCommit(false);
			
			runner.update(conn, "update account set money=15000 where name='tom'");//给一个连接，才可以开启事务
			//提交或回滚事务
			conn.commit();
			
		} catch (SQLException e) {
			try {
				conn.rollback();//回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		
		
		
	}
	
}
