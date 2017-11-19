package com.ringcentral.frolov.managers.serviceweb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by alexanderzaverukha on 11/19/17.
 */
public class LoginPage {
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

        return this;
    }

    public LoginPage setMainNumber(String mainNumber){

        return this;
    }


    public LoginPage submitAndStay(String mainNumber){

        return this;
    }

    public MainPage submit(String mainNumber){
        return new MainPage(driver);
    }



}
