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
import org.openqa.selenium.JavascriptExecutor;

import java.util.concurrent.TimeUnit;

public class Tests {
    private static WebDriver driver;

    @BeforeTest
    public static void setDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void gRequest() {
        driver.get("https://www.google.com");
        Assert.assertEquals("https://www.google.com",driver.getCurrentUrl());
        WebElement searchform = driver.findElement(By.id("lst-ib"));
        String searchRequest = "webdriver tutorial";
        searchform.sendKeys(searchRequest);
        searchform.sendKeys(Keys.ENTER);
        String searchResult = driver.findElement(By.id("lst-ib")).getAttribute("value");
        Assert.assertEquals(searchResult,searchRequest );
    }

    @Test
    public void rc(){
        driver.get("https://www.ringcentral.com");

    }

    @AfterTest
    public static void closeDriver() {
        driver.quit();
    }
}


