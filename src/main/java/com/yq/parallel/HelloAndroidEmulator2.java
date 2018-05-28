package com.yq.parallel;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;

/*
 * 在windows平台上的模拟器上执行
 */
public class HelloAndroidEmulator2 {
    private AppiumDriver<WebElement> driver;

    @BeforeMethod
    @Parameters({"appiumServerPort","udid"})
    public void setUp(String appiumServerPort,String udid) throws Exception {
        // set up appium
        /**
         * 需要在远程机器windows平台上执行。
         * apk在windwos的目录: C:/windowsgrid/appium/app-pre-debug.apk
         *
         */
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName",udid);//emulator-5556
        capabilities.setCapability("udid",udid);//必须;adb devices
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformVersion", "7.1.1");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName","uiautomator2");
        capabilities.setCapability("app", "C:/windowsgrid/appium/app-pre-debug.apk");
        capabilities.setCapability("appPackage", "com.Vcredit.manage.pre");//可以省略，只要启动activity即可
        capabilities.setCapability("appActivity", "com.Vcredit.ui.view.common.SplashActivity");//必须

        //服务器参数：session 之间不重置应用状态 (iOS: 不删除应用的 plist 文件； Android: 在创建一个新的 session 前不删除应用。)
        capabilities.setCapability("noReset",true);
        //服务器参数：允许 session 被覆盖
        capabilities.setCapability("sessionOverride", true);
        driver = new AndroidDriver<WebElement>(new URL("http://192.168.1.109:"+appiumServerPort+"/wd/hub"), capabilities);
    }//4727
    /*
    登录case
     */
    @Test
    public void yqLoginTest2() throws InterruptedException {
        Thread.sleep(3000);
        Assert.assertFalse(false);


    }
    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }
}
