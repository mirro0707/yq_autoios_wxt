package com.wxt.testcase.login;

import com.csvreader.CsvReader;
import com.wxt.base.BaseTestcase;
import io.appium.java_client.MobileBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * testcase命名规则：模块_编号_功能_Test
 * 用例描述：登录并且退出
 *
 */
public class Login_000_LoginAndExit_Test extends BaseTestcase {

    @Test(enabled = false)
    public void loginWXTTest() throws Exception {
        //登录
        myDriver.loginWXT("00100303623","1234567b");

        //校验登录成功
        myDriver.findElement(MobileBy.iOSNsPredicateString("name == '个人'")).click();
        String text = myDriver.findElement(MobileBy.iOSNsPredicateString("value like '*00100303623'")).getText();
        System.out.println(text);
        Assert.assertEquals("工号  00100303623",text);

        //如果是首次登录，获取弹出框中的内容


        //返回主页
        //退出维信通
        myDriver.findElement(MobileBy.iOSNsPredicateString("name == '个人'")).click();
        myDriver.findElement(MobileBy.iOSNsPredicateString("value == '设置'")).click();
        myDriver.findElement(MobileBy.iOSNsPredicateString("value == '退出登录'")).click();
        driver.switchTo().alert().accept();//确定退出！


    }

    @Test(enabled = false)
    public void loginWithAccountsTest() throws Exception {
//        System.out.println("源码：\n"+driver.getPageSource());//打印源码 XXX.iter

        String filePath = System.getProperty("user.dir")+"/data/csv/loginAccountRolePrivilege.csv";
        try {
            // 创建CSV读对象
            CsvReader csvReader = new CsvReader(filePath,',',Charset.forName("UTF-8"));
            // 读表头
            csvReader.readHeaders();
            int i=0;
            while (csvReader.readRecord()){
                i++;
                csvReader.getRawRecord();// 读一整行,在读取这一行的列
                //登录
                myDriver.loginWXT(csvReader.get("账户"),csvReader.get("密码"));// 读这行的列

                myDriver.exitWXT();

                if (i == 3)//读取三行
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
