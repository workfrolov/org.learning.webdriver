package com.ringcentral.frolov.managers.serviceweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by alexanderzaverukha on 11/19/17.
 */
public class LoginPage {
    //TODO home task - extract to base class BasePage
    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public LoginPage setExtension(String extension){
        WebElement extPin = driver.findElement(By.id("pin"));
        extPin.sendKeys(extension);
        return this;
    }


    public LoginPage setPassword(String password){
        WebElement extPswd = driver.findElement(By.id("password"));
        extPswd.sendKeys(password);
        return this;
    }

    public LoginPage setMainNumber(String mainNumber){

        return this;
    }


    public LoginPage submitAndStay(){
        WebElement signIn = driver.findElement(By.xpath("//button[@data-test-automation-id='signInBtn']"));
        signIn.click();
        return this;
    }

    public MainPage submit(){
        WebElement signIn = driver.findElement(By.cssSelector("button[data-test-automation-id=signInBtn]"));
        signIn.click();
        return new MainPage(driver);
    }

}
