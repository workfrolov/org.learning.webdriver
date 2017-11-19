package TestSuit;

import com.ringcentral.frolov.RCAccount;
import com.ringcentral.frolov.managers.serviceweb.Navigation;
import com.ringcentral.frolov.managers.serviceweb.ServiceWebManager;
import com.ringcentral.frolov.managers.serviceweb.SWEnv;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tests {
    private RCAccount account = new RCAccount("(678) 744-0130", "Test!123", "101");



    static final Logger LOGGER = LoggerFactory.getLogger(Tests.class);
    private static WebDriver driver;

    @BeforeTest
    public static void setDriver() {
       // System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void gRequest() {
        driver.get("https://www.google.com");
        Assert.assertEquals("https://www.google.com", driver.getCurrentUrl());
        LOGGER.info(driver.getCurrentUrl());
        WebElement searchform = driver.findElement(By.id("lst-ib"));
        String searchRequest = "webdriver tutorial";
        searchform.sendKeys(searchRequest);
        searchform.sendKeys(Keys.ENTER);
        String searchResult = driver.findElement(By.id("lst-ib")).getAttribute("value");
        Assert.assertEquals(searchResult, searchRequest);
        LOGGER.info(searchResult);
    }

    @Test
    public void rc() {
        ServiceWebManager serviceWebManager = new ServiceWebManager(driver, SWEnv.AMS);
        serviceWebManager.navigateTo(Navigation.LOGIN);
        Assert.assertEquals("https://service-amsup.lab.nordigy.ru/#/enterCredential", driver.getCurrentUrl());
        serviceWebManager.getSignIn()
                .setEmailOrPhoneNumber(account.getMainNumber())
                .next();

        LOGGER.info(driver.getCurrentUrl());
        //TODO home task - extract to page class
        String loginNumber = driver.findElement(By.xpath("//div[@id='rc-login-country-number']//input")).getAttribute("value");
        Assert.assertEquals(loginNumber, account.getMainNumber());

        //https://wiki.base22.com/display/btg/How+to+setup+SLF4J+and+LOGBack+in+a+web+app+-+fast
//extract to login
        serviceWebManager.getLoginPage().
                setExtension(account.getExtension())
                .setPassword(account.getPassword())
                .submit();
        //TODO home task - extract to page class
        String accountInfo = driver.findElement(By.cssSelector("span.extension-info")).getText();
        Assert.assertEquals(accountInfo, account.getMainNumber() + " Ext. " + account.getExtension(), accountInfo);
        LOGGER.info(driver.findElement(By.cssSelector("span.extension-info")).getText());
    }

    @AfterTest
    public static void closeDriver() {
        driver.quit();
    }
}