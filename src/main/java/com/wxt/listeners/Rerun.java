package com.wxt.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * 可以修改为从properties/config.properties里读取重跑次数
 */
public class Rerun implements IRetryAnalyzer {
    int index =0;//retryCount = 0;
    int reRunTimes=1;//定义重跑次数 maxRetryCount = 1;

    public boolean retry(ITestResult iTestResult) {
        if (index < reRunTimes) {//小于，则重跑
            /*
            写个提示
             */
            String message = "running retry for ' " + iTestResult.getName() + " ' on class " +
                    this.getClass().getName() + " Retrying " + reRunTimes + " times ";
            System.out.println(message);

            index++;
            return true;
        }
        return false;
    }
}
