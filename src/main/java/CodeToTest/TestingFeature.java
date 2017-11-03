package CodeToTest;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static CodeToTest.TestingFeature.searchResult;

public class TestingFeature {
    //public static void main(String[] args) throws InterruptedException {
    //    searchResult();
    //}
    public static void searchResult(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String startUrl = "http://www.google.com";
        driver.get(startUrl);
        String checkUrl = driver.getCurrentUrl();
        WebElement searchform = driver.findElement(By.id("searchform"));
        searchform.sendKeys("webdriver tutorial");
        searchform.sendKeys(Keys.ENTER);

    }
}
