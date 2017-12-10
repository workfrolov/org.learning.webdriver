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
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.TestCaseId;

public class Login extends BaseTest {
    static final Logger LOGGER = LoggerFactory.getLogger(Login.class);
    private RCAccount account = new RCAccount("(678) 744-0130", "Test!123", "101");

    @TestCaseId("TMS-1")
    @Features("My Feature")
    @Stories({"Story1", "Story2"})
    @Test
    public void testLogin() {
        navigateToLogin(getServiceWebManager());
        Assert.assertEquals("https://service-amsup.lab.nordigy.ru/#/enterCredential", getServiceWebManager().getDriver().getCurrentUrl());
        //TODO
       // Assert.assertEquals(serviceWebManager.getConfig().setServiceWebUrl() + "#/enterCredential", serviceWebManager.getDriver().getCurrentUrl());

        //TODO
        //check login page
        loginToMainPage(getServiceWebManager());

        Assert.assertEquals(getServiceWebManager().getMainPage().getAccountInfo(), account.getMainNumber() + " Ext. " + account.getExtension());

        LOGGER.info(getServiceWebManager().getDriver().findElement(By.cssSelector("span.extension-info")).getText());
    }

    @Step("Login to main page")
    public void loginToMainPage(ServiceWebManager serviceWebManager) {
        serviceWebManager.getSignIn()
                .setEmailOrPhoneNumber(account.getMainNumber())
                .next();

        LOGGER.info(serviceWebManager.getDriver().getCurrentUrl());
        LoginPage loginPage = serviceWebManager.getLoginPage();

        Assert.assertEquals(loginPage.getLoginNumber(), account.getMainNumber());

        loginPage.setExtension(account.getExtension())
                .setPassword(account.getPassword())
                .submit();
    }

    @Step("Navigate to login")
    public void navigateToLogin(ServiceWebManager serviceWebManager) {
        serviceWebManager.navigateTo(Navigation.LOGIN);
    }
}