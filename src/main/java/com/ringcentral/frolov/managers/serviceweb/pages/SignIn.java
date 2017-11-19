package com.ringcentral.frolov.managers.serviceweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by alexanderzaverukha on 11/19/17.
 */
public class SignIn {
    WebDriver driver;

    public SignIn(WebDriver driver){
        this.driver = driver;
    }

    public SignIn setEmailOrPhoneNumber(String emailOrPhoneNumber){
        WebElement credentialValue = driver.findElement(By.id("credential"));
        credentialValue.sendKeys(emailOrPhoneNumber);
        return this;
    }

    public void next(){
        WebElement nextButton = driver.findElement(By.xpath("//button[@data-test-automation-id='loginCredentialNext']"));
        nextButton.click();
    }

}
