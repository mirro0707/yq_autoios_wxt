package com.yq.example;

import com.wxt.util.C3P0Utils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class YQDatabaseTest {
    @Test
    public void databaseTest() throws Exception {
        //后台将此账号置为首次登陆

        // select * from app_user where user_no = '00100303623'
        // update app_user set is_first_login = '1' where user_no = '00100303623'
        String sql = "update app_user set is_first_login = ? where user_no = ?";
        Object[] params = {"1","00100303623"};

        Connection conn = C3P0Utils.getConnection();//连接池获取一个连接(采用配置文件配置数据源c3p0-config.xml)

        QueryRunner queryRunner = new QueryRunner();//创建SQL执行器
        int rows = 0;
        try {

            rows = queryRunner.update(conn, sql,params);//执行SQL插入
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        logger.info("成功执行" + rows + "条数据！");
        System.out.println("成功执行" + rows + "条数据！");

        DbUtils.closeQuietly(conn);//关闭（归还）数据库连接



    }
}
