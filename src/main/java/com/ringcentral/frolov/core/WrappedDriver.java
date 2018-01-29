package com.ringcentral.frolov.core;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Created by alexanderzaverukha on 12/23/17.
 */
public class WrappedDriver{
    static final Logger LOGGER = LoggerFactory.getLogger(WrappedDriver.class);
    private static WrappedDriver instance = null;
    private static WebDriver driver = null;
    private WrappedDriver(){
    }
    //button[@data-test-automation-id='loginCredentialNext']

   // public void click("loginCredentialNext")
   public void click(String locator){
       LOGGER.info("Click on: '" + locator + "'");
        String xpath = "//*[@data-test-automation-id='" + locator + "']";

        this.getDriver().findElement(By.xpath(xpath)).click();
       LOGGER.info("Click on: '" + locator + "' successfull");
   }

   public void setValue(String locator, String value){
       LOGGER.info("Set value " + "'"+ value + "' " + "to: '" + locator + "'");
        driver.findElement(By.xpath(locator)).sendKeys(value);
       LOGGER.info("Set value " + "'value' " + "to: '" + locator + "' was successfull");
   }


    public String getValue(String locator){
        LOGGER.info("Get value from: '" + locator + "'");
        String text = driver.findElement(By.xpath(locator)).getText();
        LOGGER.info("Get value from: '" + locator + "' returns: " + text);
        return text;
    }

   public static WrappedDriver get(){
       if(instance == null)
           instance = new WrappedDriver();
        return instance;
   }

    public  WebDriver getDriver() {
        if(driver == null){
            LOGGER.debug("Init WebDriver");
            System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("driverPathProperty"));
            ChromeOptions caps = new ChromeOptions();
              LoggingPreferences logs = new LoggingPreferences();
              logs.enable(LogType.DRIVER, Level.FINEST);
              logs.enable(LogType.BROWSER, Level.FINEST);
            caps.setCapability(CapabilityType.LOGGING_PREFS, logs);
            caps.setCapability(CapabilityType.PLATFORM, Platform.ANY);
            driver = new ChromeDriver(caps);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            LOGGER.debug("Init WebDriver Done");
        }
        return driver;
    }

    public void stop(){
        LOGGER.debug("Stopping WebDriver");
        if(getDriver()!=null) {

            getDriver().quit();
        }
        LOGGER.debug("Stopped WebDriver");
    }

}
