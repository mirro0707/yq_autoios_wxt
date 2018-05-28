package com.wxt.base;

import com.wxt.tools.LoggerControler;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class BaseTestcase {
    protected static IOSDriver driver;
    protected static MyDriver myDriver;
    protected static LoggerControler log = LoggerControler.getLogger(BaseTestcase.class);

    ;//定义日志输出对象

    @BeforeMethod
    public void setUp() throws Exception {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File app = new File(classpathRoot, "/apps/ios/Vcredit.app");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 7 Plus");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");//xcode8.0以上把uiautomation废掉了,9.3或以上！
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ios");
        capabilities.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID,"83A7ZY5DET");
        capabilities.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID,"iPhone Developer");
        capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID,"com.Vcredit.Manage.pre");

        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);//不卸载安装
        capabilities.setCapability(MobileCapabilityType.SUPPORTS_ALERTS, true);
        capabilities.setCapability("simpleIsVisibleCheck", true);//起什么作用？

//        capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, "true");//连app自己弹出来的alert也自动被KO了？也没有效果！
        capabilities.setCapability(IOSMobileCapabilityType.WAIT_FOR_APP_SCRIPT, "$.delay(5000); $.acceptAlert(); true;");//等待appOK的时候执行设置的js,那么不安装的时候也执行吗？？！！无效果！
        capabilities.setCapability(IOSMobileCapabilityType.SUPPORTS_JAVASCRIPT, "true");
//        capabilities.setCapability("locationServicesAuthorized", true);
//        capabilities.setCapability("waitForAppScript", "$.delay(5000); $.acceptAlert(); true;");


        driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        myDriver = new MyDriver(driver);
    }


    @AfterMethod
    public void tearDown() throws Exception {
        if (driver!= null) {
            //根据包名查找应用名称
//            myDriver.closeApp(PropertiesDataProvider.getPropertiesData(configFilePath, appPackage));//getPropertiesData(String configFilePath, String key)
            driver.quit(); //关闭并释放清理所有driver
        } else {
            Assert.fail("driver没有获得对象,退出操作失败");

        }
    }
    //读取CSV文件的静态方法，使用CSV文件的绝对文件路径作为函数参数
    public static Object[][] getSearchData(String FileNameroot) throws IOException {
        List<Object[]> records=new ArrayList<Object[]>();
        String record;
        //设定UTF-8字符集，使用带缓冲区的字符输入流BufferedReader读取文件内容
        BufferedReader file=new BufferedReader(new InputStreamReader(new FileInputStream(FileNameroot),"UTF-8"));
        //忽略读取CSV文件的标题行（第一行）
        file.readLine();
        //遍历读取文件中除第一行外的其他所有内容并存储在名为records的ArrayList中，每一行records中存储的对象为一个String数组
        while((record=file.readLine())!=null){
            String fields[]=record.split(",");
            records.add(fields);
        }
        //关闭文件对象
        file.close();
        //将存储测试数据的List转换为一个Object的二维数组
        Object[][] results=new Object[records.size()][];
        //设置二位数组每行的值，每行是一个Object对象
        for(int i=0;i<records.size();i++){
            results[i]=records.get(i);
        }
        return results;
    }

    /**
     * 线程等待
     * @param millis
     */
    public void myWait(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
