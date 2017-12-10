package com.ringcentral.frolov.tests.serviceweb;

import com.ringcentral.frolov.RCAccount;
import com.ringcentral.frolov.managers.serviceweb.components.Navigation;
import com.ringcentral.frolov.managers.serviceweb.ServiceWebManager;
import com.ringcentral.frolov.managers.serviceweb.pages.LoginPage;
import com.ringcentral.frolov.tests.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Login extends BaseTest {
    static final Logger LOGGER = LoggerFactory.getLogger(Login.class);
    private RCAccount account = new RCAccount("(678) 744-0130", "Test!123", "101");

    @Test
    public void testLogin() {
        ServiceWebManager serviceWebManager = getServiceWebManager();
        serviceWebManager.navigateTo(Navigation.LOGIN);
        Assert.assertEquals("https://service-amsup.lab.nordigy.ru/#/enterCredential", serviceWebManager.getDriver().getCurrentUrl());
        //TODO
       // Assert.assertEquals(serviceWebManager.getConfig().setServiceWebUrl() + "#/enterCredential", serviceWebManager.getDriver().getCurrentUrl());

        //TODO
        //check login page
        serviceWebManager.getSignIn()
                .setEmailOrPhoneNumber(account.getMainNumber())
                .next();

        LOGGER.info(serviceWebManager.getDriver().getCurrentUrl());
        LoginPage loginPage = serviceWebManager.getLoginPage();

        Assert.assertEquals(loginPage.getLoginNumber(), account.getMainNumber());

        loginPage.setExtension(account.getExtension())
                .setPassword(account.getPassword())
                .submit();

        Assert.assertEquals(serviceWebManager.getMainPage().getAccountInfo(), account.getMainNumber() + " Ext. " + account.getExtension());

        LOGGER.info(serviceWebManager.getDriver().findElement(By.cssSelector("span.extension-info")).getText());
    }
}