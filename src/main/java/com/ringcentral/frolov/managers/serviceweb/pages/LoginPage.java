package com.ringcentral.frolov.managers.serviceweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by alexanderzaverukha on 11/19/17.
 */
public class LoginPage extends BasePage {
    //TODO home task - extract to base class BasePagea
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginToSW(String mainNumber, String password, String extension) {
        setMainNumber(mainNumber);
        getLoginNumber();
        setExtension(extension);
        setPassword(password);
        submit();

    }

}