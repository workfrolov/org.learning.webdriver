package com.ringcentral.frolov.suit;

import com.ringcentral.frolov.RCAccount;
import com.ringcentral.frolov.managers.serviceweb.Navigation;
import com.ringcentral.frolov.managers.serviceweb.ServiceWebManager;
import com.ringcentral.frolov.managers.serviceweb.SWEnv;
import com.ringcentral.frolov.managers.serviceweb.pages.BasePage;
import com.ringcentral.frolov.managers.serviceweb.pages.LoginPage;
import com.ringcentral.frolov.managers.serviceweb.pages.MainPage;
import com.ringcentral.frolov.managers.serviceweb.pages.SignIn;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginToSWtest extends BasePage {
    static final Logger LOGGER = LoggerFactory.getLogger(LoginToSWtest.class);
    private RCAccount account = new RCAccount("(678) 744-0130", "Test!123", "101");
    LoginPage loginPage = new LoginPage(driver);
    MainPage mainPage = new MainPage(driver);
    SignIn signIn = new SignIn(driver);

    public LoginToSWtest(WebDriver driver) {
        super(driver);
    }

    @Test
    public void rc() {
        ServiceWebManager serviceWebManager = new ServiceWebManager(driver, SWEnv.AMS);
        serviceWebManager.navigateTo(Navigation.LOGIN);
        Assert.assertEquals("https://service-amsup.lab.nordigy.ru/#/enterCredential", driver.getCurrentUrl());
        serviceWebManager.getSignIn()
                .setEmailOrPhoneNumber(account.getMainNumber())
                .next();

        LOGGER.info(driver.getCurrentUrl());

        Assert.assertEquals(loginPage.getLoginNumber(), account.getMainNumber());

        loginPage.setExtension(account.getExtension())
                .setPassword(account.getPassword())
                .submit();

        Assert.assertEquals(mainPage.getAccountInfo(), account.getMainNumber() + " Ext. " + account.getExtension());

        LOGGER.info(driver.findElement(By.cssSelector("span.extension-info")).getText());
    }
}