package com.wxt.testcase.personalcenter;

import com.wxt.base.BaseTestcase;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * testcase命名规则：模块_编号_功能_Test
 * 用例描述：
 * 个人中心->
 * 修改密码
 * 修改成功，请重新登陆
 "
 *
 */
public class personalcenter_001__changpwd_Test extends BaseTestcase {


    @Test
    public void changpwd() {
        String account = "00100303623";
        String password = "654321";
        String newPassword = "1234567b";
        boolean flag = false;

        //登陆
        myDriver.loginWXT(account,password);//赵勇飞
        //修改成功后立即跳转到登陆页面,并出现toast:修改成功请重新登录
        flag = changPassword(password, newPassword);//修改密码成功后的toast捕获不到！



        //重新登录
        myDriver.loginWXT(account, newPassword);


        if (myDriver.doesElementExist(MobileBy.AccessibilityId("个人")))
            flag = true;

        //把密码改回来

        changPassword(newPassword, password);

        Assert.assertEquals(flag,true);




    }

    private boolean changPassword(String password, String newPassword) {
        //进入个人中心
        myDriver.findElement(MobileBy.AccessibilityId("个人")).click();

        //点击修改密码
        myDriver.findElement(MobileBy.AccessibilityId("修改密码")).click();



        //输入原密码和新密码
        myDriver.findElement(MobileBy.AccessibilityId("请输入当前登录密码")).sendKeys(password);
        myDriver.findElement(MobileBy.AccessibilityId("密码长度4-18位")).sendKeys(newPassword);
        myDriver.findElements(MobileBy.AccessibilityId("密码长度4-18位")).get(1).sendKeys(newPassword);
        driver.hideKeyboard();
        myDriver.findElement(MobileBy.AccessibilityId("确  定")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        WebElement element1 = myDriver.findElement(MobileBy.iOSNsPredicateString("value like '修改成功*'"));
//        System.out.println(element1.getText());
//        WebElement element = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(MobileBy.iOSNsPredicateString("修改成功,请重新登录！")));
//        logger.info("寻找toast成功:"+element.getText());
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("源码：\n"+driver.getPageSource());
//        WebElement element = myDriver.findElement(MobileBy.iOSNsPredicateString("value like '修改成功*请重新登录*'"));
//        System.out.println("tishi:"+element.getText());
//        boolean flag = myDriver.findToast(MobileBy.iOSNsPredicateString("value like '修改成功*请重新登录*'"));
//        return  myDriver.findToastByXpath("修改成功,请重新登录！");
        return myDriver.findToastByIosNsPredicatedString("修改成功,请重新登录！");


    }

}

