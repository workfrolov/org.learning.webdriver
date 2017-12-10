package com.ringcentral.frolov.managers.serviceweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by alexanderzaverukha on 11/19/17.
 */
public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver){
       super(driver);
    }

    @Step("Set extension: {0}")
    public LoginPage setExtension(String extension){
        WebElement extPin = getDriver().findElement(By.id("pin"));
        extPin.sendKeys(extension);
        saveScreenshot("setExtension");
        return this;
    }

    @Step("Set password: {0}")
    public LoginPage setPassword(String password){
        WebElement extPswd = getDriver().findElement(By.id("password"));
        extPswd.sendKeys(password);
        saveScreenshot("setPassword");
        return this;
    }

    @Step("Set main number: {0}")
    public LoginPage setMainNumber(String mainNumber){

        return this;
    }

    @Step("Submit and stay on page")
    public LoginPage submitAndStay(){
        WebElement signIn = getDriver().findElement(By.xpath("//button[@data-test-automation-id='signInBtn']"));
        signIn.click();
        return this;
    }

    @Step("Submit to main page")
    public MainPage submit(){
        WebElement signIn = getDriver().findElement(By.cssSelector("button[data-test-automation-id=signInBtn]"));
        signIn.click();
        return new MainPage(getDriver());
    }

    @Step("Get login number")
    public String getLoginNumber(){
        return getDriver().findElement(By.xpath("//div[@id='rc-login-country-number']//input")).getAttribute("value");
    }

}