package com.ringcentral.frolov.tests.serviceweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ringcentral.frolov.RCAccount;
import com.ringcentral.frolov.core.Config;
import com.ringcentral.frolov.managers.serviceweb.SWEnv;
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
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Login extends BaseTest {
    static final Logger LOGGER = LoggerFactory.getLogger(Login.class);
    //private RCAccount account = new RCAccount("(678) 744-0130", "Test!123", "101");
    ServiceWebManager serviceWebManager;
    Config config;

    @BeforeTest
    public void before() throws IOException, URISyntaxException {
        serviceWebManager = new ServiceWebManager();
        //TODO set config
        //serviceWebManager.setConfig();


    }

    @AfterTest
    public void after() {
        if (serviceWebManager != null)
            serviceWebManager.stop();
    }

    @Test
    public void testLogin() {
        serviceWebManager.navigateTo(Navigation.LOGIN);
        LOGGER.info(serviceWebManager.getDriver().getCurrentUrl());
        serviceWebManager.loginIndependentLoginPage();
        LOGGER.info(serviceWebManager.getMainPage().getAccountInfo());
        serviceWebManager.result();
    }

}
//      serviceWebManager.compareURL();
        //Assert.assertEquals("https://service-amsup.lab.nordigy.ru/#/enterCredential", serviceWebManager.getDriver().getCurrentUrl());
        //TODO - Done - The Assert use getConfig() + moved to ServiceWebManager class (The compareURL() method)
        //Assert.assertEquals(serviceWebManager.getConfig().getServiceWebUrl() + "/#/enterCredential", serviceWebManager.getDriver().getCurrentUrl());

        //TODO check login page - Done via try-catch; moved to the ServiceWebManager class
        //check login page
//        if (serviceWebManager.compareURL()) {
//            serviceWebManager.getSignIn()
//                    .setEmailOrPhoneNumber(account.getMainNumber())
//                    .next();
//
//            LOGGER.info(serviceWebManager.getDriver().getCurrentUrl());
//            LoginPage loginPage = serviceWebManager.getLoginPage();
//
//
//            Assert.assertEquals(serviceWebManager.getLoginPage().getLoginNumber(), account.getMainNumber());
//
//            serviceWebManager.getLoginPage().setExtension(account.getExtension())
//                    .setPassword(account.getPassword())
//                    .submit();
//
//            Assert.assertEquals(serviceWebManager.getMainPage().getAccountInfo(), account.getMainNumber() + " Ext. " + account.getExtension());
//
//            LOGGER.info(serviceWebManager.getDriver().findElement(By.cssSelector("span.extension-info")).getText());
//        } else {
//            serviceWebManager.getLoginPage()
//                    .setMainNumber(account.getMainNumber())
//                    .setExtension(account.getExtension())
//                    .setPassword(account.getPassword())
//                    .submit();
//
//            Assert.assertEquals(serviceWebManager.getMainPage().getAccountInfo(), account.getMainNumber() + " Ext. " + account.getExtension());
//
//            LOGGER.info(serviceWebManager.getDriver().findElement(By.cssSelector("span.extension-info")).getText());
//        }
//
//    }
//}
