package com.yq.example;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;

public class Iphone8plus_simulator {
    IOSDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File app = new File(classpathRoot, "/apps/ios/Vcredit.app");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
//        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");//ios10,10.3.1(14E8301)
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.3");//8.3
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8 Plus");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");//xcode8.0以上把uiautomation废掉了
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ios");
        capabilities.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID,"83A7ZY5DET");
        capabilities.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID,"iPhone Developer");
        capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID,"com.Vcredit.Manage.pre");
//        capabilities.setCapability(MobileCapabilityType.UDID, "3ea2d2e89716346de5aa6672a18393501c66bec4");
//        capabilities.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, "8100");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);//不卸载安装


        driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        
    }

    @Test
    public void testName() throws Exception {

        String s1= "OA";
        String temp = "value == '" + s1 + "'";
        System.out.println("源码：\n"+driver.getPageSource());
//        System.out.println(myDriver.findElement(MobileBy.iOSNsPredicateString("name == '维信邮箱'")));//找不到
        driver.findElementByIosNsPredicate("name == 'OA'").click();


    }


    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();

    }
}
