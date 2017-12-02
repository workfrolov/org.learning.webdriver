package com.ringcentral.frolov.managers.serviceweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by alexanderzaverukha on 11/19/17.
 */
public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }


    public String getLoginNumber(){
        return getDriver().findElement(By.xpath("//div[@id='rc-login-country-number']//input")).getAttribute("value");
    }

    public String getAccountInfo() {
        return getDriver().findElement(By.cssSelector("span.extension-info")).getText();
    }
}