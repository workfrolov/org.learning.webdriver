package com.ringcentral.frolov.tests.serviceweb;

import com.ringcentral.frolov.RCAccount;
import com.ringcentral.frolov.managers.serviceweb.components.Navigation;
import com.ringcentral.frolov.managers.serviceweb.ServiceWebManager;
import com.ringcentral.frolov.managers.serviceweb.pages.LoginPage;
import com.ringcentral.frolov.tests.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

public class Login extends BaseTest {
    static final Logger LOGGER = LoggerFactory.getLogger(Login.class);
    private RCAccount account = new RCAccount("(678) 744-0130", "Test!123", "101");
    ServiceWebManager serviceWebManager;

    @BeforeTest
    public void before() throws IOException, URISyntaxException {
        serviceWebManager = new ServiceWebManager();
    }

    @AfterTest
    public void after(){
        if(serviceWebManager != null)
            serviceWebManager.stop();
    }

    @Test
    public void testLogin() {
        serviceWebManager.navigateTo(Navigation.LOGIN);
        Assert.assertEquals("https://service-amsup.lab.nordigy.ru/#/enterCredential", serviceWebManager.getDriver().getCurrentUrl());
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