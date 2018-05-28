package com.yq.parallel;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;

/**
 * 不可加入失败截图，独立运行，未加入框架内
 */
public class HelloIosSimulator1 {
    private AppiumDriver<WebElement> driver;


    @BeforeMethod
    @Parameters({"appiumServerPort","udid"})
    public void setUp(String appiumServerPort,String udid) throws Exception {
        // set up appium
        File classpathRoot = new File(System.getProperty("user.dir"));
        File app = new File(classpathRoot, "apps/ios/Vcredit.app");
//        File app = new File(classpathRoot, "app"+ File.separator+"Vcredit.app");//编译完放在那里
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");//ios10,10.3.1(14E8301)
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, udid);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");//xcode8.0以上把uiautomation废掉了
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ios");
//        capabilities.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, "8100");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);//不卸载安装

//        driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver = new IOSDriver<WebElement>(new URL("http://192.168.1.106:4723/wd/hub"), capabilities);

    }
    /*
    登录case
     */
    @Test
    public void yqLoginTest3() throws InterruptedException {
        Thread.sleep(3000);
        Assert.assertFalse(false);


    }
    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }
}
