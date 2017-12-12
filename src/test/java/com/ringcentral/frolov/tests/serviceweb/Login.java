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
        loginToMainPage(getServiceWebManager());
        result(getServiceWebManager());
    }


    @Step("Navigate to login")
    public void navigateToLogin(ServiceWebManager serviceWebManager) {
        serviceWebManager.navigateTo(Navigation.LOGIN);
        Assert.assertEquals("https://service-amsup.lab.nordigy.ru/#/enterCredential", getServiceWebManager().getDriver().getCurrentUrl());
    }

    @Step("Login to main page")
    public void loginToMainPage(ServiceWebManager serviceWebManager) {
        try {
            serviceWebManager.getDriver().findElement(By.xpath("//button[@data-test-automation-id='loginCredentialNext']")).isDisplayed();
            LOGGER.info(getServiceWebManager().getDriver().getCurrentUrl());
            unifiedEnterCredentialPage(serviceWebManager);
            unifiedLoginViaPhoneNumber(serviceWebManager);

        } catch (org.openqa.selenium.NoSuchElementException e) {
            LOGGER.info(getServiceWebManager().getDriver().getCurrentUrl());
            notUnifiedLoginViaPhoneNumber(serviceWebManager);
        }
    }

    @Step("Unified Login Page: Enter the phone number and proceed to tne next page")
    public void unifiedEnterCredentialPage(ServiceWebManager serviceWebManager) {
        serviceWebManager.getSignIn()
                .setEmailOrPhoneNumber(account.getMainNumber())
                .next();
    }

    @Step("Unified Login Page: Enter the extention and the password > Login to account")
    public void unifiedLoginViaPhoneNumber(ServiceWebManager serviceWebManager) {
        serviceWebManager.getLoginPage()
                .setExtensionUnified(account.getExtension())
                .setPasswordUnified(account.getPassword())
                .submitUnified();
    }

    @Step("Not Unified Login Page: Enter the phone number, extention and the password > Login to account")
    public void notUnifiedLoginViaPhoneNumber(ServiceWebManager serviceWebManager) {
        serviceWebManager.getLoginPage()
                .setMainNumber(account.getMainNumber())
                .setExtension(account.getExtension())
                .setPassword(account.getPassword())
                .submit();
    }

    @Step("Check the account main number and ext info")
    public void result(ServiceWebManager serviceWebManager) {
        Assert.assertEquals(serviceWebManager.getMainPage().getAccountInfo(), account.getMainNumber() + " Ext. " + account.getExtension());
        LOGGER.info(getServiceWebManager().getDriver().findElement(By.cssSelector("span.extension-info")).getText());
    }
}
