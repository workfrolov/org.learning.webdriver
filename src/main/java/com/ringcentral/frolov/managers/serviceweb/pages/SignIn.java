package com.ringcentral.frolov.managers.serviceweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * Created by alexanderzaverukha on 11/19/17.
 */
public class SignIn extends BasePage {
    WebDriver driver;


    public SignIn(WebDriver driver) {
        super(driver);
    }

    public void signInToLoginPage(String mainNumber) {
        setEmailOrPhoneNumber(mainNumber);
        next();
    }

}
