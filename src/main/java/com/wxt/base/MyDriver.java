package com.wxt.base;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxt.tools.LoggerControler;
import com.wxt.util.C3P0Utils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


/**
 * 《随着API版本更新，这个类要随时更新》
 * appium API封装：使框架中所有的基本操作都从这个类中去调用，实现统一性（待完善和更新）
 * 元素等待+异常捕获+log输出
 */

public class MyDriver {
    protected IOSDriver driver;
//    public ITestResult it;
    private final long DEFAULT_TIMEOUT = 10;//用户忍耐时间
    private final long LONG_TIMEOUT = 20;

    public static LoggerControler log = LoggerControler.getLogger(MyDriver.class);

    public MyDriver(IOSDriver mydriver) {
        driver =  mydriver;
    }



     // 维信通公共方法


     /**
     * 登录维信通
     * @param username 用户名称
     * @param pwd 密码
     * @throws Exception
     */
    public void loginWXT(String username, String pwd){
        //检查是否已经登录,登录了先退出
        if (isLogin()){
            exitWXT();
        }

        //输入用户名
        WebElement acc = findElement(MobileBy.iOSNsPredicateString("type =='XCUIElementTypeTextField'"));
        acc.clear();
        acc.sendKeys(username);
        driver.hideKeyboard();

        //输入密码
        findElement(MobileBy.iOSNsPredicateString("type =='XCUIElementTypeSecureTextField'")).sendKeys(pwd);
        driver.hideKeyboard();


        //如果未勾选登录说明，请先登陆勾选
        if (doesElementExist(MobileBy.AccessibilityId("icon_check_a"))){
            findElement(MobileBy.AccessibilityId("icon_check_a")).click();
        }

        //点击登录
        findElement(MobileBy.iOSNsPredicateString("name like '登*录'")).click();

        //等待页面加载成功
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //如果首次登陆会有公告出现，处理首次登陆的公告
        if (doesElementExist(MobileBy.AccessibilityId("btn cls"))) {
            findElement(MobileBy.AccessibilityId("btn cls")).click();
        }
        //查看是否登录成功（首次会有公告，不合适！）
//        driver.findElementByIosNsPredicate("name = '个人'").click();
//        System.out.println("value = '手机 "+username+"'");
//        WebElement iphone = driver.findElementByIosNsPredicate("value == '手机 "+username+"'");//谓词怎么变成变量???
//        if (("手机 "+username).equalsIgnoreCase(iphone.getAttribute("value"))){
//            logger.info(username+"登录成功！");
//            System.out.println(username+"登录成功！");
//        }


    }

    public void loginWXTWithClickLoginDescription(String username, String pwd){


        //输入用户名
        WebElement acc = findElement(MobileBy.iOSNsPredicateString("type =='XCUIElementTypeTextField'"));
        acc.clear();
        acc.sendKeys(username);
        driver.hideKeyboard();

        //输入密码
        findElement(MobileBy.iOSNsPredicateString("type =='XCUIElementTypeSecureTextField'")).sendKeys(pwd);
        driver.hideKeyboard();
        //点击登录说明
        findElement(MobileBy.xpath("//XCUIElementTypeApplication[@name=\"维信金科(yq)\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[2]")).click();

        if (doesElementExist(MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeButton'"))) {
            findElement(MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeButton'")).click();//点击返回，继续登录
        }


        //点击登录
        findElement(MobileBy.iOSNsPredicateString("name like '登*录'")).click();

        //等待页面加载成功
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    /**
     *appVersion = 2.4.0
     * deviceModel = iPhone9%2C2
     * deviceType = 2
     * mobileNo = 15151406980
     * sign = kxaVEwgLRS3Z2N87vv2kNqVnE8otvlbB0h4qhna0WL87lTbnvZuK7PZ0cb0QLbbY2iJcQtdKet9cQOVWTF1PzJQDA1Tk9vDfNbLAkYC2tPweZJ3JrvMqVr0xiZdnv/PcybGygqSJP4XzQ9l4WiRjwam75QxPD6UtA%2BjitW7tz4Q%3D
     * sysVersion = 11.0
     *userNo = 00100303623
     *
     * @return 点击"获取验证码"，获取验证码(固定到某台机器，不用计算签名）
     */
    public String getMobileCodeByFirstBindAccount() throws IOException {
        String returnContent = Request.Post("http://153.37.192.99:8088/wxApp/api/v1/common/getMobileCode")
                .bodyForm(Form.form()
                        .add("appVersion", "2.4.0")
                        .add("deviceModel", "iPhone9,2")
                        .add("deviceType", "2")
                        .add("mobileNo", "15151406980")
                        .add("sign", "kxaVEwgLRS3Z2N87vv2kNqVnE8otvlbB0h4qhna0WL87lTbnvZuK7PZ0cb0QLbbY2iJcQtdKet9cQOVWTF1PzJQDA1Tk9vDfNbLAkYC2tPweZJ3JrvMqVr0xiZdnv/PcybGygqSJP4XzQ9l4WiRjwam75QxPD6UtA+jitW7tz4Q=")
                        .add("sysVersion", "11.0")
                        .add("userNo", "00100303623").build())
                .execute().returnContent().asString();

        String jsonString = returnContent;
        JSONObject jsonObject = JSON.parseObject(jsonString);//解析Json字符串
        String mobileCode = jsonObject.get("data").toString();//获取key为data的值


        log.info("获取到的验证码为："+mobileCode);
        log.info("返回内容"+returnContent);
        return mobileCode;
    }


    /**
     * sql占位符为？
     * @param sql update or insert or delete
     * @param params
     * @return 影响的行数
     */
    public int executeSql(String sql, Object... params){
        Connection conn = C3P0Utils.getConnection();//连接池获取一个连接(采用配置文件配置数据源c3p0-config.xml)

        QueryRunner queryRunner = new QueryRunner();//创建SQL执行器
        int rows = 0;
        try {

            rows = queryRunner.update(conn, sql,params);//执行SQL插入
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("成功执行" + rows + "条数据！");
//        System.out.println("成功执行" + rows + "条数据！");

        DbUtils.closeQuietly(conn);//关闭（归还）数据库连接
        return  rows;
    }

    /**
     * 查询一个数据
     * @param sql
     * @param params
     * @return
     */
    public <T> T querySqlOne(String sql, Object... params) {
        Connection conn = C3P0Utils.getConnection();//连接池获取一个连接

        QueryRunner qr = new QueryRunner();
         T result = null;
        try {
            result = (T) qr.query(conn, sql, new ScalarHandler("is_vbs"), params);

        } catch (SQLException e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        }
        DbUtils.closeQuietly(conn);//关闭（归还）数据库连接
        return result;
    }

    public int setFirstLogin(String account){
        String sql = "update app_user set is_first_login = ? where user_no = ?";
        Object[] params = {"1",account};
        return executeSql(sql, params);
    }

    /**
     * 查找某个元素是否存在
     * @param by
     * @return
     */
    public boolean doesElementExist(By by) {
        try {
            findElement(by);
            /*WebElement  e = findElement(by);//只报超市
            System.out.println("name:"+e.getAttribute("name"));*/

            return true;
        } catch (NoSuchElementException nee) {

            return false;
        } catch (TimeoutException te){
            return false;
        }

    }
    /**
     * 判断某账户是否绑成功绑定vbs
     * 是否成功绑定VBS账号，1表示没有，0表示成功绑定
     */
    public Boolean isBingVBS(String wxtaccount){
        int result = 2;
        result = querySqlOne("select * from app_user where user_no = ?", wxtaccount);
        System.out.println("is_vbs:"+result);
        if (result == 0){
            return  true;
        }else if(result == 1){
            return false;
        }else{
            throw new RuntimeException("vbs绑定判断的值不是0也不是1");
        }
    }

    /**
     * 解绑VBS账户
     *
     */
    public Boolean BindVBS(String vbsaccount, String vbspassword){
        try {
            findElement(MobileBy.iOSNsPredicateString("name == '个人'")).click();
            findElement(MobileBy.iOSNsPredicateString("name == 'VBS绑定'")).click();
            findElement(MobileBy.iOSNsPredicateString("value == '请输入您的账户名'")).sendKeys(vbsaccount);
            findElement(MobileBy.iOSNsPredicateString("value == '请输入您的密码'")).sendKeys(vbspassword);
            findElement(MobileBy.iOSNsPredicateString("name like '确*定'")).click();

            Thread.sleep(2000);


        } catch (Exception e){
            return  false;
        }
        return true;


    }
    /**
     * 解绑VBS账户
     * @param account
     * @return
     */
    public Boolean UnBindVBS(String account){
        try {
            findElement(MobileBy.iOSNsPredicateString("name == '个人'")).click();
            findElement(MobileBy.iOSNsPredicateString("name == 'VBS解绑'")).click();
            findElement(MobileBy.iOSNsPredicateString("name == '解绑'")).click();
            driver.switchTo().alert().accept();//确定退出！

        }catch (Exception e){
            return  false;
        }

        return true;

    }

    public boolean findToast(By by){
        try {
            WebElement webElement = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(by));
            log.info("寻找toast成功!");
            System.out.println(webElement.getAttribute("value"));
            return true;
        }catch (Exception e){
            log.info("寻找toast失败");
            return false;

        }



    }
    public boolean findToastByXpath(String toast) {
        try {
            new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[contains(@value,'"+ toast + "')]")));
            log.info("寻找toast成功:"+toast);
            return true;
        } catch (Exception e) {
            log.info("寻找toast失败");
            return false;

        }
    }
    public boolean findToastByIosNsPredicatedString(String toast) {
        String t = "value == '"+toast+"'";
        try {
            WebElement element = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(MobileBy.iOSNsPredicateString(t)));
            log.info("寻找toast成功:"+toast);
            System.out.println("toast:"+toast);
            return true;
        } catch (Exception e) {
            log.info("寻找toast失败");
            return false;

        }
    }
//根据方向来滑动
    public void scroll(String direction) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", direction);
        js.executeScript("mobile: scroll", scrollObject);
    }
//根据坐标来滑动
    public void swipe(Double startX, Double startY, Double endX,
                             Double endY, Double duration, int repeat) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObj = new HashMap<String, Double>();
        swipeObj.put("startX", startX);
        swipeObj.put("startY", startY);
        swipeObj.put("endX", endX);
        swipeObj.put("endY", endY);
        swipeObj.put("duration", duration);
        // 拖动
        for (int i = 0; i < repeat; i++) {
            try {
                js.executeScript("mobile: swipe", swipeObj);
            } catch (WebDriverException ex) {
//                saveScreenShot();
//                log.logException(ex);
            }
        }
    }

    //滑动到某个元素
    public void  swipetoelement(WebElement element){
        // Java


        JavascriptExecutor js = (JavascriptExecutor) driver;
//        element = findElement(MobileBy.iOSNsPredicateString(""));
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: scroll", scrollObject);


    }
    public void  scroll(){
        // Java



    }

    /**
     * 不推荐使用，每次使用后应该直接退出！！！！！！！
     * depreciated
     * 退出维信通（个人tag可见时）
     */
    public void exitWXT(){
        findElement(MobileBy.iOSNsPredicateString("name == '个人'")).click();
        findElement(MobileBy.iOSNsPredicateString("value == '设置'")).click();
        findElement(MobileBy.iOSNsPredicateString("value == '退出登录'")).click();
        driver.switchTo().alert().accept();

    }
    /**
     * depreciated
     * 检查是否有人登录
     */
    public boolean isLogin(){
        if (doesElementExist(MobileBy.iOSNsPredicateString("name = '个人'")))
            return  true;
        return false;
    }

    /**
     * depreciated
     * 检查某人是否登录
     */
    public boolean isLogin(String username){
        if (doesElementExist(MobileBy.iOSNsPredicateString("name = '个人'"))) {
            findElement(MobileBy.iOSNsPredicateString("name = '个人'")).click();//点击个人tag

            String text = findElement(MobileBy.iOSNsPredicateString("value = '工号 00100303623'")).getText();
            if (text.contains(username))
                return true;
        }
        return false;
    }


/*    public boolean doesElementExist(String iosNsPredicate) {
        try {
//            findElement(byElement);
            driver.findElementByIosNsPredicate("name like 'icon_check_a'");
            return true;
        } catch (NoSuchElementException nee) {

            return false;
        }

    }*/

    /*
     * 封装常见定位方法
     */

/*
    public void waitForElement(MobileBy mobileBy){//改造了等待元素的方法
        IOSDriverWait wait = new IOSDriverWait(driver, DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mobileBy));
    }

    public void waitForElementForLongTime(MobileBy mobileBy){
        IOSDriverWait wait = new IOSDriverWait(driver, DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mobileBy));
    }
*/

    /**
     * 元素等待的封装有问题！！！！！！ ios元素判断的不对！！！！
     * @param by
     */
    public void waitForElement(By by){
        WebDriverWait wait = new WebDriverWait(driver,DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForElementForLongTime(By by){
        WebDriverWait wait = new WebDriverWait(driver,LONG_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * 通过By对象 去查找某个元素
     */

    public WebElement findElement(By by) {
//        waitForElement(by);//暂时不用！！重新封装！
        return driver.findElement(by);
    }

    /**
     * 通过By对象 去查找一组元素
     */
    public List<WebElement> findElements(By by) {
        waitForElement(by);
        return driver.findElements(by);
    }


    /**
     * 关闭所有driver并清理临时文件,所以多线程不能使用！
     */
    public void quit() {
        driver.quit();
        log.info("driver已被清理");
    }




}
