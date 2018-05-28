package com.wxt.testcase.homepage;

import com.wxt.base.BaseTestcase;
import com.wxt.util.C3P0Utils;
import io.appium.java_client.MobileBy;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * testcase命名规则：模块_编号_功能_Test
 * 用例描述：
 * 顶部应用控制
 * app_user.is_vbs 是否成功绑定VBS账号，1表示没有，0表示成功绑定
 "
 *
 */
public class homepage_001__TopPrivilegeCheck_Test extends BaseTestcase {

    @DataProvider(name = "topControlData")
    public static Object[][] data() {
        return new Object[][]{
                {"普通员工", "00100100037", "654321", "OA、邮箱、任务、通讯录","lumin","1234567b"},
                {"客户经理未绑定VBS", "00100100296", "654321", "OA、产品、任务、通讯录","lvyang","654321"},
                {"客户经理已绑定VBS", "00100100296", "654321", "订单搜索、业绩报表、任务、通讯录","lvyang","654321"}
        };
    }

    @Test(dataProvider = "topControlData")
    public void topApplicationPrivilegeCheck(String role, String account, String password, String result,String vbsaccount,String vbspassword) {
        String[] s = result.split("、");
        myDriver.loginWXT(account, password);

        if (role.equals("普通员工")) {
            boolean flag = false;
            flag = isResultExsit(s, flag);
            myDriver.exitWXT();
            Assert.assertEquals(flag,true);
        } else if (role.equals("客户经理未绑定VBS")) {
            //如果已经绑定，先解绑
            if (myDriver.isBingVBS(account)) {
                myDriver.UnBindVBS(account);
                //退出，重新登录加载
//                myDriver.exitWXT();
//                myDriver.loginWXT(account, password);
                //回到首页
                myDriver.findElement(MobileBy.iOSNsPredicateString("name == '首页'")).click();
            }

            boolean flag = false;
            flag = isResultExsit(s, flag);
            myDriver.exitWXT();
            Assert.assertEquals(flag,true);
        } else if (role.equals("客户经理已绑定VBS")) {
            //如果没有绑定，先绑定
            if (!myDriver.isBingVBS(account)) {
                myDriver.BindVBS(vbsaccount,vbspassword);
                //退出，重新登录加载首页
                /*myDriver.exitWXT();
                myDriver.loginWXT(account, password);*/
                //回到首页
                myDriver.findElement(MobileBy.iOSNsPredicateString("name == '首页'")).click();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            boolean flag = false;
            flag = isResultExsit(s, flag);
            myDriver.exitWXT();
            Assert.assertEquals(flag,true);

        }else{
            log.info("出错！");
        }


    }
    /*
    查找结果中每个字符串是否在首页顶部出现
     */
    private boolean isResultExsit(String[] s, boolean flag) {
        for (String s1 : s) {
//            System.out.println("s1"+s1);
//            String temp = "value == '" + s1 + "'";
//            flag = myDriver.doesElementExist(MobileBy.iOSNsPredicateString(temp));
//            flag = myDriver.doesElementExist(MobileBy.AccessibilityId("OA"));
            System.out.println("s1:"+s1);
            flag = myDriver.doesElementExist(MobileBy.AccessibilityId(s1));
            System.out.println("flag:"+flag);

            if (flag == false) {
//                Assert.assertEquals(true, flag);//一旦出错就终止了，影响退出！
                break;
            }
        }
        return flag;
    }

}
