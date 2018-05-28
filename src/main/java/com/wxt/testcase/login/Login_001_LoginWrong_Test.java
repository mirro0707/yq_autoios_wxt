package com.wxt.testcase.login;

import com.wxt.base.BaseTestcase;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * testcase命名规则：模块_编号_功能_Test
 * 用例描述：
 * 用户名和密码输入校验
 *
 *
 */
public class Login_001_LoginWrong_Test extends BaseTestcase {

    @DataProvider(name="loginData")
    public static Object[][] data() throws IOException
    {
        String filePath = System.getProperty("user.dir")+"/data/csv/loginData.csv";
        return getSearchData(filePath);//获取CSV文件的测试数据
    }

    @Test(dataProvider = "loginData")
    public void loginWithWrongTest(String username, String password,String result,String description) throws Exception {
        //检查是否已经登录,登录了先退出
        if (myDriver.isLogin()){
            myDriver.exitWXT();
        }

        //输入用户名
        WebElement acc = myDriver.findElement(MobileBy.iOSNsPredicateString("type =='XCUIElementTypeTextField'"));
        acc.clear();
        acc.sendKeys(username);
        driver.hideKeyboard();
        System.out.println("username:"+username);

        //输入密码
        myDriver.findElement(MobileBy.iOSNsPredicateString("type =='XCUIElementTypeSecureTextField'")).sendKeys(password);
        driver.hideKeyboard();
        System.out.println("password:"+password);

        //点击登录
        myDriver.findElement(MobileBy.iOSNsPredicateString("name like '登*录'")).click();



        //断言
       String toast = result;
        Assert.assertEquals(myDriver.findToastByIosNsPredicatedString(toast),true);
//        Assert.assertEquals(flag,true);


    }


}
