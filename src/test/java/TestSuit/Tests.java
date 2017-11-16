package TestSuit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Tests {
    private static WebDriver driver;

    @BeforeTest
    public static void setDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void gRequest() {
        driver.get("https://www.google.com");
        Assert.assertEquals("https://www.google.com", driver.getCurrentUrl());
        WebElement searchform = driver.findElement(By.id("lst-ib"));
        String searchRequest = "webdriver tutorial";
        searchform.sendKeys(searchRequest);
        searchform.sendKeys(Keys.ENTER);
        String searchResult = driver.findElement(By.id("lst-ib")).getAttribute("value");
        Assert.assertEquals(searchResult, searchRequest);
    }

    @Test
    public void rc() {
        String loginPage = "https://service-amsup.lab.nordigy.ru/#/enterCredential";
        String mainNumber = "(678) 744-0130";
        String ext = " 101";
        String pswd = "Test!123";
        driver.get(loginPage);
        Assert.assertEquals("https://service-amsup.lab.nordigy.ru/#/enterCredential", driver.getCurrentUrl());
        WebElement credentialValue = driver.findElement(By.id("credential"));
        credentialValue.sendKeys(mainNumber);
        WebElement nextButton = driver.findElement(By.xpath("//div[@class='text-center']"));
        nextButton.click();

        //String loginNumber = driver.findElement(By.id("usernameFormGroup")).getAttribute("value");
        //Assert.assertEquals(loginNumber, mainNumber);

        WebElement extPin = driver.findElement(By.id("pin"));
        extPin.sendKeys(ext);
        WebElement extPswd = driver.findElement(By.id("password"));
        extPswd.sendKeys(pswd);
        WebElement signIn = driver.findElement(By.cssSelector("button.btn.btn-primary"));
        signIn.click();
        String accountInfo = driver.findElement(By.cssSelector("span.extension-info")).getText();
        Assert.assertEquals(mainNumber + " Ext." + ext, accountInfo);
    }

    @AfterTest
    public static void closeDriver() {
        driver.quit();
    }
}