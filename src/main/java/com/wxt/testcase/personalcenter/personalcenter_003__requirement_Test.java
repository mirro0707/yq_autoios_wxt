package com.wxt.testcase.personalcenter;

import com.wxt.base.BaseTestcase;
import com.wxt.tools.DateFormate;
import com.wxt.tools.LoggerControler;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

/**
 * testcase命名规则：模块_编号_功能_Test
 * 创建产品意见
 *
 *
 */
public class personalcenter_003__requirement_Test extends BaseTestcase {
    LoggerControler log = LoggerControler.getLogger(personalcenter_003__requirement_Test.class);


    @Test
    @Parameters({"zyf_account","zyf_password"})
    public void createRequirement(String account, String password) {
//        String account = "00100303623";//赵勇飞
//        String password = "654321";


        String requirement = "创建需求autoTest"+DateFormate.format(DateFormate.DEFAULT_DATE_FORMAT);//产品的问题和意见
        String requirementReply = "后台需求回复autoTest"+DateFormate.format(DateFormate.DEFAULT_DATE_FORMAT);//产品的问题和意见
        boolean flag = false;

        //登陆
        myDriver.loginWXT(account,password);
        //进入个人中心
        myDriver.findElement(MobileBy.AccessibilityId("个人")).click();

//        System.out.println("源码：\n"+driver.getPageSource());

        myDriver.findElement(MobileBy.AccessibilityId("需求反馈")).click();

        myDriver.findElement(MobileBy.AccessibilityId("我也要提需求")).click();

//        myDriver.findElement(MobileBy.AccessibilityId("需求反馈")).click();
        myDriver.findElement(MobileBy.iOSNsPredicateString("name == '需求反馈' && type == 'XCUIElementTypeButton'")).click();

        myDriver.findElement(MobileBy.AccessibilityId("请简要描述你的问题和意见")).sendKeys(requirement);
        driver.hideKeyboard();

//        myDriver.findElement(MobileBy.AccessibilityId("提 交")).click();
        myDriver.findElement(MobileBy.iOSNsPredicateString("name like '提*交' ")).click();


        log.info("需求提交成功！");
        //登录后台http://10.155.20.160:8080/wxApp/a/appFeedback/feedBackList
        //后台处理产品意见或需求的回复
        backReply(requirement,requirementReply);

        log.info("后台需求回复成功");

        //校验创建和后台回复均成功才ok
        Assert.assertTrue(true);





//        System.out.println("源码：\n"+driver.getPageSource());



    }

    public void  backReply(String productIdea,String productIdeaReply){

        System.setProperty("webdriver.chrome.driver","./drivers/mac/chromedriver");//设置chrome的实际安装路径
        ChromeDriver chromeDriver = new ChromeDriver();

        myWait(2000);

        chromeDriver.get("http://10.155.20.160:8080/");

        myWait(2000);

        chromeDriver.manage().deleteAllCookies(); // 删除cookie里的内容

        Cookie ck1 = new Cookie("Hm_lpvt_82116c626a8d504a5c0675073362ef6f", "1509780167"); // 初始化已经保存了登录信息的cookie
        Cookie ck2 = new Cookie("Hm_lvt_82116c626a8d504a5c0675073362ef6f", "1509678800,1509780109");
        Cookie ck3 = new Cookie("JSESSIONID", "F9F2C92AA0FCC14B323904C8D2258774");
        Cookie ck4 = new Cookie("rememberMe", "u7noTkolajzzfK1Ljy1W/zFqE2SwJzg87bV0FQaVAr6kL9Jvj+U7E9cBS9yxWDi0sWVozy1/YwDDJub0dawcBEI0fPHowlhqoA0XFPU/Hq5WjaxZDW1akDI+U7WbO3nzRKTuyNSW9Rp8HHhco0rHDtNOmYBtPJwptgWzPX3Kl9rRnZmYQreRUXSjG0FMh87MmO2xmUrskqLLgdugDpiHj8WdH/5dcM/QLJuo0uQGhu6XfxACTlNKyRSUyriBjmtKZ97HZLLyfla+wHdXcSBjb0v1V15pXWmg+vrkN7g2f7HUWSxA2XMmf3dLEY4A+78T29+jMpMmIY/rxnU1trzJzvhkFUI40moXaZ9ygJMd9hSke5C6D0EgqWHsvqFamwQEz+nHwC7pPQaxMdOo/YjvbwGFtTyLSpG7Lyvv61grMBNDAZOQyr6iQavagOzkF72ggfU1TlKV9k6Ga2yLeEqJrtuDuG7yMaYuEMi9jB+QU0TTgjdvj0/tHvsSntYpNOEez4zYzEYqbSN2Zod2ALcP9qUbJdyv7nf8HyksGX4u5xIUdp8z+S+sVcaUADCj19VIx5F8T2ms2gvF+9vUGXB4anINbKZpNoDbbKFJnXMzAz4AWsJRfN8HjuY5iDPJ1gEsCO4+/yFglWC83Au98je8VTDih8Vu3dd19xAhG8Qn1u+P7G0SrtI+wR/KbZ0wKgw6SGH43TZrAfYtQyDpireo2YKP4W8opzWv9Q7EHEGPnbuH6pyOams1laswbahD9J9Kpn1FxlpuRqv8gsWEpSP7h8JNpXB/ycf8axdxt4ovluUC4IGBxQOK932NiWhC7gcW");

        chromeDriver.manage().addCookie(ck1); // webdriver添加cookie
        chromeDriver.manage().addCookie(ck2);
        chromeDriver.manage().addCookie(ck3);
        chromeDriver.manage().addCookie(ck4);

        chromeDriver.get("http://10.155.20.160:8080/wxApp/a/appFeedback/feedBackList");


//        System.out.println(chromeDriver.manage().getCookies());

        //后台处理产品意见或需求的回复

        //找到table下的所有行
        List<WebElement> rows = chromeDriver.findElements(By.xpath("//*[@id='contentTable']/tbody/tr"));
        //只处理一条
        for (int i = 1; i <= rows.size(); i++) {
            String title = chromeDriver.findElement(By.xpath("//*[@id='contentTable']/tbody/tr["+i+"]/td[2]/a")).getAttribute("title");
            System.out.println("title:"+title);
            if (title.equals(productIdea)){
                //点击进入
                myWait(1000);
                chromeDriver.findElement(By.xpath("//*[@id='contentTable']/tbody/tr["+i+"]/td[10]/a[1]")).click();
                //输入回复内容
                chromeDriver.findElement(By.id("replyContent")).sendKeys(productIdeaReply);
                //提交
                chromeDriver.findElement(By.id("btnSubmit")).click();

                break;//跳出for循环
            }
        }

        chromeDriver.quit();

    }


    @Test
    public void test(){

        myDriver.findElement(MobileBy.AccessibilityId("个人")).click();

//        System.out.println("源码：\n"+driver.getPageSource());

        myDriver.findElement(MobileBy.AccessibilityId("需求反馈")).click();

        myDriver.findElement(MobileBy.AccessibilityId("我也要提需求")).click();

        myDriver.findElement(MobileBy.iOSNsPredicateString("name == '需求反馈' && type == 'XCUIElementTypeButton'")).click();

        myWait(5000);
    }


}

