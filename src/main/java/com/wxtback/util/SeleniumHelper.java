package com.wxtback.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumHelper {

    public static String tableCell(WebDriver driver, int row, int column)
    {
        String text = null;
        //去掉表头
        row=row+1;
        String xpath="//*[@id='xxxx']/tbody/tr["+row+"]/td["+column+"]";
        WebElement table=driver.findElement(By.xpath(xpath));
        text=table.getText();
        return text;
    }
}
