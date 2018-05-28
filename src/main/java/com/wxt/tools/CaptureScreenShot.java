package com.wxt.tools;

import com.wxt.base.BaseTestcase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CaptureScreenShot extends BaseTestcase {
    public  static void screenShot(ITestResult iTestResult) {
        String passfailMethod = iTestResult.getMethod().getRealClass().getSimpleName()+"."+iTestResult.getMethod().getMethodName();
        // 获取当前工作目录
        String currentPath = System.getProperty("user.dir");
        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String destFilePath = currentPath+"/results/screenshots/failures/"+passfailMethod + "_" + dateFormat.format(new Date()) + ".jpg";
        try {
            FileUtils.copyFile(srcFile, new File( destFilePath));
        } catch (IOException e) {
            log.error("截图失败！！");
            e.printStackTrace();
        }
    }

/*    public static void screenShot2(ITestResult iTestResult){
        String passfailMethod = iTestResult.getMethod().getRealClass().getSimpleName()+"."+iTestResult.getMethod().getMethodName();
        // 获取当前工作目录
        String currentPath = System.getProperty("user.dir");
        //获取screenshot文件
        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        String destFilePath = currentPath+"/results/screenshots/failures/"+passfailMethod+"_"+getDatetime()+".jpg";
        //文件copy到指定的文件夹
        try {
            FileUtils.copyFile(srcFile,new File(destFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取系统时间

    public static String getDatetime(){
        SimpleDateFormat date = new SimpleDateFormat("yyyymmdd_hhmmss");
        return date.format(new Date());

    }*/
}
