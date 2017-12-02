package com.ringcentral.frolov.managers.serviceweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by alexanderzaverukha on 11/19/17.
 */
public class LoginPage extends BasePage {
    //TODO home task - extract to base class BasePagea


    public LoginPage(WebDriver driver){
       super(driver);
    }

    public LoginPage setExtension(String extension){
        WebElement extPin = getDriver().findElement(By.id("pin"));
        extPin.sendKeys(extension);
        return this;
    }


    public LoginPage setPassword(String password){
        WebElement extPswd = getDriver().findElement(By.id("password"));
        extPswd.sendKeys(password);
        return this;
    }

    public LoginPage setMainNumber(String mainNumber){

        return this;
    }


    public LoginPage submitAndStay(){
        WebElement signIn = getDriver().findElement(By.xpath("//button[@data-test-automation-id='signInBtn']"));
        signIn.click();
        return this;
    }

    public MainPage submit(){
        WebElement signIn = getDriver().findElement(By.cssSelector("button[data-test-automation-id=signInBtn]"));
        signIn.click();
        return new MainPage(getDriver());
    }

    public String getLoginNumber(){
        return getDriver().findElement(By.xpath("//div[@id='rc-login-country-number']//input")).getAttribute("value");
    }

}