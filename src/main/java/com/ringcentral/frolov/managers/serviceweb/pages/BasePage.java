package com.ringcentral.frolov.managers.serviceweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 30.11.2017.
 */
public class BasePage {
    private WebDriver driver;
    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    protected WebDriver getDriver(){
        return driver;
    }
}
