package com.ringcentral.frolov.tests.serviceweb;

import com.ringcentral.frolov.managers.serviceweb.ServiceWebManager;
import com.ringcentral.frolov.managers.serviceweb.components.Navigation;
import com.ringcentral.frolov.managers.serviceweb.pages.MainPage;
import com.ringcentral.frolov.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by user on 12.12.2017.
 */
public class SignUp extends BaseTest {
    static final Logger LOGGER = LoggerFactory.getLogger(Login.class);

    @BeforeTest
    public void before() {
        getServiceWebManager();
    }

    @AfterTest
    public void after() {
        if (getServiceWebManager() != null)
            getServiceWebManager().stop();
    }


    @Test
    public void testSignIn(){
        navigateToLogin(getServiceWebManager());
        clickSignUpHere(getServiceWebManager());
        result(getServiceWebManager());
    }

    @Step("Navigate to login")
    public void navigateToLogin(ServiceWebManager serviceWebManager) {
        serviceWebManager.navigateTo(Navigation.LOGIN);
        Assert.assertEquals("https://service-amsup.lab.nordigy.ru/#/enterCredential", getServiceWebManager().getDriver().getCurrentUrl());
    }

    @Step("Click the 'Sign up here' link")
    public MainPage clickSignUpHere(ServiceWebManager serviceWebManager) {
        WebElement signIn = serviceWebManager.getDriver().findElement(By.xpath("//*[@id=\"rc-gen11\"]/div/div[1]/div/div[1]/div[2]/div[2]/a"));
        signIn.click();
        return new MainPage();
    }

    @Step("Check the the page link where was redirected")
    public void result(ServiceWebManager serviceWebManager) {
        Assert.assertEquals("http://service-amsup-us.lab.nordigy.ru/office/plansandpricing.html", getServiceWebManager().getDriver().getCurrentUrl());
        LOGGER.info(getServiceWebManager().getDriver().getCurrentUrl());
    }
}
