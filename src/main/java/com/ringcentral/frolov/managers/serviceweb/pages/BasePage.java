package com.ringcentral.frolov.managers.serviceweb.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.allure.annotations.Attachment;

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

    @Attachment(value = "{0}", type = "image/png")
    public byte[] saveScreenshot(String name) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
