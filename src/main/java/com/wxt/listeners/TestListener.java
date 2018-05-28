package com.wxt.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.wxt.tools.CaptureScreenShot.screenShot;

/**
 * 失败错误截图
 */
public class TestListener implements ITestListener{
    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {

    }

    /**
     * 错误时候进行截图
     * @param iTestResult
     */
    public void onTestFailure(ITestResult iTestResult) {
        screenShot(iTestResult);
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }
}
