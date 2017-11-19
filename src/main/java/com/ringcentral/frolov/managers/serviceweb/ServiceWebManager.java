package com.ringcentral.frolov.managers.serviceweb;

import com.ringcentral.frolov.RCAccount;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by alexanderzaverukha on 11/19/17.
 */
public class ServiceWebManager {
    static final Logger LOGGER = LoggerFactory.getLogger(ServiceWebManager.class);
    WebDriver driver;
    String swEnv;
    public ServiceWebManager(WebDriver driver, SWEnv env){
        this(driver, env.toString());
    }

    public ServiceWebManager(WebDriver driver, String env){
        this.driver = driver;
        this.swEnv = env;
    }

    public ServiceWebManager login(RCAccount account){
        LOGGER.info(String.format("[ServiceWebManager#login] %s", account));
        navigateTo(Navigation.LOGIN);
        //
//        WebElement extPswd = driver.findElement(By.id("password"));
//        extPswd.sendKeys(pswd);
//        WebElement signIn = driver.findElement(By.cssSelector("button.btn.btn-primary"));
//        signIn.click();


        return this;
    }

    private void navigateTo(Navigation navigation) {
        driver.get(getEntry(navigation));
    }

    public String getEntry(Navigation navigation){
        return this.swEnv+ navigation.toString();
    }
}
