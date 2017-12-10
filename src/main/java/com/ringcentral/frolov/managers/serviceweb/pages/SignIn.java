package com.ringcentral.frolov.managers.serviceweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.*;
import com.ringcentral.frolov.managers.serviceweb.ServiceWebManager;


/**
 * Created by alexanderzaverukha on 11/19/17.
 */
public class SignIn extends BasePage {


    public SignIn(WebDriver driver){
        super(driver);
    }

    public SignIn setEmailOrPhoneNumber(String emailOrPhoneNumber){
        WebElement credentialValue = getDriver().findElement(By.id("credential"));
        credentialValue.sendKeys(emailOrPhoneNumber);
        return this;
    }

    public void next(){
        WebElement nextButton = getDriver().findElement(By.xpath("//button[@data-test-automation-id='loginCredentialNext']"));
        nextButton.click();
    }


}
