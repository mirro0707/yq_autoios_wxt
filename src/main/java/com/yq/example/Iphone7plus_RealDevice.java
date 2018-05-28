package com.yq.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;

public class Iphone7plus_RealDevice {
    IOSDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File app = new File(classpathRoot, "/apps/ios/Vcredit.app");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");//ios10,10.3.1(14E8301)
//        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");//ios10,10.3.1(14E8301)
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "yangiphone7p");
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8 Plus");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");//xcode8.0以上把uiautomation废掉了
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ios");
        capabilities.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID,"83A7ZY5DET");
        capabilities.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID,"iPhone Developer");
        capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID,"com.Vcredit.Manage.pre");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);//不卸载安装

//        capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, "true");//连app自己弹出来的alert也自动被KO了？也没有效果！
        capabilities.setCapability(IOSMobileCapabilityType.WAIT_FOR_APP_SCRIPT, "$.delay(5000); $.acceptAlert(); true;");//等待appOK的时候执行设置的js,那么不安装的时候也执行吗？？！！无效果！
        capabilities.setCapability(IOSMobileCapabilityType.SUPPORTS_JAVASCRIPT, "true");
//        capabilities.setCapability("locationServicesAuthorized", true);
//        capabilities.setCapability("waitForAppScript", "$.delay(5000); $.acceptAlert(); true;");

        capabilities.setCapability(MobileCapabilityType.UDID, "3ea2d2e89716346de5aa6672a18393501c66bec4");


        driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        
    }

    @Test
    public void testName() throws Exception {

        Assert.assertFalse(false);
//        Thread.sleep(2000);

    }


    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();

    }
}
