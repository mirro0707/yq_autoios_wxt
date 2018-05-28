package com.wxt.testcase.login;

import com.csvreader.CsvReader;
import com.wxt.base.BaseTestcase;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * testcase命名规则：模块_编号_功能_Test
 * 用例描述：
 * VBS业务权限校验
 *
 *
 */
public class Login_002_LoginRolePrivilege_Test extends BaseTestcase {

    @DataProvider(name="rolePrivilegeData")
    public static Object[][] data() throws IOException
    {
        String filePath = System.getProperty("user.dir")+"/data/csv/loginAccountRolePrivilege.csv";
        return getSearchData(filePath);//获取CSV文件的测试数据
    }

    @Test(dataProvider = "rolePrivilegeData")
    public void verifyRolePermissionTest(String role,String account, String password,String vbsname,String vbspassword,String privilege,String description){
//        System.out.println("源码：\n"+driver.getPageSource());//打印源码 XXX.iter


                myDriver.loginWXT(account,password);




                myDriver.findElement(MobileBy.iOSNsPredicateString("name == '工作台'")).click();

                //滑动
                JavascriptExecutor js = (JavascriptExecutor) driver;
                HashMap<String, String> scrollObject = new HashMap<String, String>();
                scrollObject.put("direction", "down");
                js.executeScript("mobile: scroll", scrollObject);
//                System.out.println("源码：\n"+driver.getPageSource());
                boolean flag = true;


                if (privilege.equals("无")){
                    flag = !myDriver.doesElementExist(MobileBy.iOSNsPredicateString("value == '业务'"));//应该找不到业务模块
                }else{
                    String[] s = privilege.split("、");
                    String iOSNsPredicateString="";
                    for (String s1 : s) {
                        iOSNsPredicateString = "value == '"+s1+"'";
                        flag = flag&myDriver.doesElementExist(MobileBy.iOSNsPredicateString(iOSNsPredicateString));//有一个没有找到则为false
                    }

                }



                //退出
                myDriver.exitWXT();
                Assert.assertEquals(flag,true);//Assert一旦失败会中断执行




    }






}
