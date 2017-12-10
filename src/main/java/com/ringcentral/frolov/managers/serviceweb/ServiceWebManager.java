package com.ringcentral.frolov.managers.serviceweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ringcentral.frolov.RCAccount;
import com.ringcentral.frolov.core.Config;
import com.ringcentral.frolov.managers.serviceweb.components.Navigation;
import com.ringcentral.frolov.managers.serviceweb.pages.LoginPage;
import com.ringcentral.frolov.managers.serviceweb.pages.MainPage;
import com.ringcentral.frolov.managers.serviceweb.pages.SignIn;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;


import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderzaverukha on 11/19/17.
 */
public class ServiceWebManager {
    static final Logger LOGGER = LoggerFactory.getLogger(ServiceWebManager.class);
    WebDriver driver;

    //******* PAGES
    String swEnv;
    LoginPage loginPage;
    SignIn signIn;
    MainPage mainPage;
    Config config;
    RCAccount account;

    public ServiceWebManager() throws IOException, URISyntaxException {
        initConfig();
        System.setProperty("webdriver.chrome.driver", config.getDriverPath());
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.swEnv = config.getServiceWebUrl();

        loginPage = new LoginPage(driver);
        signIn = new SignIn(driver);
        mainPage = new MainPage(driver);
        account = new RCAccount("(678) 744-0130", "Test!123", "101");

    }

    private void initConfig() throws IOException, URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL configUrl = classLoader.getResource("config.json");
        byte[] jsonData = Files.readAllBytes(Paths.get(configUrl.toURI()));
        ObjectMapper objectMapper = new ObjectMapper();
        config = objectMapper.readValue(jsonData, Config.class);
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig() {
        this.config = getConfig();
    }

    public ServiceWebManager login(RCAccount account) {
        LOGGER.info(String.format("[ServiceWebManager#login] %s", account));
        navigateTo(Navigation.LOGIN);
        signIn.setEmailOrPhoneNumber(account.getMainNumber());
        signIn.next();
        loginPage.setExtension(account.getExtension())
                .setPassword(account.getPassword())
                .submit();
        return this;
    }

    public void navigateTo(Navigation navigation) {
        driver.get(getEntry(navigation));
    }

    private String getEntry(Navigation navigation) {
        return this.swEnv + navigation.toString();
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public SignIn getSignIn() {
        return signIn;
    }

    public MainPage getMainPage() {
        return mainPage;
    }

    public void stop() {
        if (driver != null) {
            System.out.println("Closing chrome browser");
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    //**************************************************************************************

    public void unifiedLogin() {
        Assert.assertEquals(getConfig().getServiceWebUrl() + "/#/enterCredential", getDriver().getCurrentUrl());

        getSignIn()
                .setEmailOrPhoneNumber(account.getMainNumber())
                .next();

        getLoginPage()
                .setExtensionUnified(account.getExtension())
                .setPasswordUnified(account.getPassword())
                .submitUnified();

        LOGGER.info(getDriver().findElement(By.cssSelector("span.extension-info")).getText());


    }

    public void notUnifiedLogin() {
        Assert.assertEquals(getConfig().getServiceWebUrl() + "/#/number", getDriver().getCurrentUrl());

        getLoginPage()
                .setMainNumber(account.getMainNumber())
                .setExtension(account.getExtension())
                .setPassword(account.getPassword())
                .submit();

        LOGGER.info(getDriver().findElement(By.cssSelector("span.extension-info")).getText());
    }

        public void loginIndependentLoginPage(){
        try {
            getDriver().findElement(By.xpath("//button[@data-test-automation-id='loginCredentialNext']")).isDisplayed();
            unifiedLogin();
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            notUnifiedLogin();
        }
    }

    public void result(){
        Assert.assertEquals(getMainPage().getAccountInfo(), account.getMainNumber() + " Ext. " + account.getExtension());
    }

    //**************************************************************************************
}


