package com.ringcentral.frolov.suit;

import com.ringcentral.frolov.RCAccount;
import com.ringcentral.frolov.managers.serviceweb.components.Navigation;
import com.ringcentral.frolov.managers.serviceweb.ServiceWebManager;
import com.ringcentral.frolov.managers.serviceweb.SWEnv;
import com.ringcentral.frolov.managers.serviceweb.pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginToSWtest extends BaseTest{
    static final Logger LOGGER = LoggerFactory.getLogger(LoginToSWtest.class);
    private RCAccount account = new RCAccount("(678) 744-0130", "Test!123", "101");
    @Test
    public void testLogin() {
        ServiceWebManager serviceWebManager = new ServiceWebManager(getDriver(), SWEnv.AMS);
        serviceWebManager.navigateTo(Navigation.LOGIN);
        Assert.assertEquals("https://service-amsup.lab.nordigy.ru/#/enterCredential", getDriver().getCurrentUrl());
        serviceWebManager.getSignIn()
                .setEmailOrPhoneNumber(account.getMainNumber())
                .next();

        LOGGER.info(getDriver().getCurrentUrl());
        LoginPage loginPage = serviceWebManager.getLoginPage();

        Assert.assertEquals(loginPage.getLoginNumber(), account.getMainNumber());

        loginPage.setExtension(account.getExtension())
                .setPassword(account.getPassword())
                .submit();

        Assert.assertEquals(serviceWebManager.getMainPage().getAccountInfo(), account.getMainNumber() + " Ext. " + account.getExtension());

        LOGGER.info(getDriver().findElement(By.cssSelector("span.extension-info")).getText());
    }
}