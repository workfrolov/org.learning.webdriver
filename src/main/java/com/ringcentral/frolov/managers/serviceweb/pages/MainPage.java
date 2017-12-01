package com.ringcentral.frolov.managers.serviceweb.pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by alexanderzaverukha on 11/19/17.
 */
public class MainPage extends BasePage {
    WebDriver driver;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void mainPageInfo() {
        getAccountInfo();
    }
}
