package com.wxt.testcase.login;


import com.wxt.base.BaseTestcase;

import com.wxt.tools.LoggerControler;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**
 * testcase命名规则：模块_编号_功能_Test
 * 用例描述：
 *
 *
 */
public class Login_000_LoginFirst_Test extends BaseTestcase {
    LoggerControler log = LoggerControler.getLogger(Login_000_LoginFirst_Test.class);

    /**
     * 首次登陆后，需要绑定手机号，VBS账号（如果有）
     * 绑定手机，需要手机验证码
     * VBS也暂不进行自动化
     * 公告出现条件：设备或app是首次使用，数据存在客户端
     *
     *
     */
    @Test
    public void loginFirstTest() throws Exception {
        //首次登陆使用默认密码
        myDriver.loginWXT("00100303623","654321");

        myDriver.findElement(MobileBy.iOSNsPredicateString("value == '请输入您的手机号'")).sendKeys("15151406980");

        myWait(2000);
//        driver.hideKeyboard();//为什么报错？

        myWait(2000);
        //获取验证码
        //appVersion = 2.4.0
        //deviceModel = iPhone9%2C2
        //deviceType = 2
        //mobileNo = 15151406980
        //sign = kxaVEwgLRS3Z2N87vv2kNqVnE8otvlbB0h4qhna0WL87lTbnvZuK7PZ0cb0QLbbY2iJcQtdKet9cQOVWTF1PzJQDA1Tk9vDfNbLAkYC2tPweZJ3JrvMqVr0xiZdnv/PcybGygqSJP4XzQ9l4WiRjwam75QxPD6UtA%2BjitW7tz4Q%3D
        //sysVersion = 11.0
        //userNo = 00100303623
        String mobileCode = myDriver.getMobileCodeByFirstBindAccount();//点击发送验证码，并获取验证码


        //输入验证码
        myDriver.findElement(MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeTextField' && value like '验证码*'")).sendKeys(mobileCode);

        //点击下一步
        myDriver.findElement(MobileBy.AccessibilityId("下一步")).click();

        myWait(5000);


        //退出
//        myDriver.exitWXT();



    }



    /**
     * 卸载安装
     *
     */
//    @Test(enabled = false)
    @Test(enabled = false)
    public void loginFirstTest2() {

        myDriver.loginWXT("00100303623","654321");

        myDriver.findElement(MobileBy.iOSNsPredicateString("value == '请输入您的手机号'")).sendKeys("15151406980");

        //输入验证码
        WebElement element = myDriver.findElement(MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeTextField' && value like '验证码*'"));
//        WebElement element = driver.findElementByIosNsPredicate("type == 'XCUIElementTypeTextField' && value like '验证码*'");

        System.out.println(element.getText());
        element.sendKeys("123456s");
    }










}
