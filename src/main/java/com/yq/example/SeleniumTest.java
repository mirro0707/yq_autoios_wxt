package com.yq.example;

import com.wxt.tools.DateFormate;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SeleniumTest {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","./drivers/mac/chromedriver");//设置chrome的实际安装路径
        ChromeDriver chromeDriver = new ChromeDriver();
        Thread.sleep(2000);

        chromeDriver.get("http://10.155.20.160:8080/");
        Thread.sleep(2000);
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
        String productIdea = "创建产品意见autoTest2017-11-07 10:46:13";
        String productIdeaReply = "后台回复autoTest"+ DateFormate.format(DateFormate.DEFAULT_DATE_FORMAT);//产品的问题和意见

        //找到table下的所有行
        List<WebElement> rows = chromeDriver.findElements(By.xpath("//*[@id='contentTable']/tbody/tr"));
        //只处理一条
        for (int i = 1; i <= rows.size(); i++) {
            String title = chromeDriver.findElement(By.xpath("//*[@id='contentTable']/tbody/tr["+i+"]/td[2]/a")).getAttribute("title");
            if (title.equals(productIdea)){
                //点击进入
                chromeDriver.findElement(By.xpath("//*[@id='contentTable']/tbody/tr["+i+"]/td[10]/a[1]")).click();
                //输入回复内容
                chromeDriver.findElement(By.id("replyContent")).sendKeys(productIdeaReply);
                //提交
                chromeDriver.findElement(By.id("btnSubmit")).click();

                break;//跳出for循环
            }
        }

//        chromeDriver.quit();
    }
}
