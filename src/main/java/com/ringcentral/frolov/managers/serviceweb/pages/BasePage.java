package com.ringcentral.frolov.managers.serviceweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by user on 30.11.2017.
 */
public class BasePage {
    static String driverPath = "C:\\Users\\user\\chromedriver\\";
    public WebDriver driver;

    public BasePage(WebDriver driver) {
        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
        driver = new ChromeDriver();
        this.driver = driver;
    }

    public BasePage setExtension(String extension) {
        WebElement extPin = driver.findElement(By.id("pin"));
        extPin.sendKeys(extension);
        return this;
    }

    public BasePage setPassword(String password) {
        WebElement extPswd = driver.findElement(By.id("password"));
        extPswd.sendKeys(password);
        return this;
    }

    public BasePage setMainNumber(String mainNumber) {
        WebElement mainNumberfield = driver.findElement(By.xpath("*[@id=\"usernameFormGroup\"]"));
        mainNumberfield.sendKeys(mainNumber);
        return this;
    }

    public BasePage submitAndStay() {
        WebElement signIn = driver.findElement(By.xpath("//button[@data-test-automation-id='signInBtn']"));
        signIn.click();
        return this;
    }

    public BasePage submit() {
        WebElement signIn = driver.findElement(By.cssSelector("button[data-test-automation-id=signInBtn]"));
        signIn.click();
        return new BasePage(driver);
    }

    public BasePage setEmailOrPhoneNumber(String emailOrPhoneNumber) {
        WebElement credentialValue = driver.findElement(By.id("credential"));
        credentialValue.sendKeys(emailOrPhoneNumber);
        return this;
    }

    public void next() {
        WebElement nextButton = driver.findElement(By.xpath("//button[@data-test-automation-id='loginCredentialNext']"));
        nextButton.click();
    }

    private String loginNumber = driver.findElement(By.xpath("//div[@id='rc-login-country-number']//input")).getAttribute("value");

    public String getLoginNumber() {
        return loginNumber;
    }

    private String accountInfo = driver.findElement(By.cssSelector("span.extension-info")).getText();

    public String getAccountInfo() {
        return accountInfo;
    }
}
