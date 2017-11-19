package com.ringcentral.frolov.managers.serviceweb;

import com.ringcentral.frolov.RCAccount;
import com.ringcentral.frolov.managers.serviceweb.pages.LoginPage;
import com.ringcentral.frolov.managers.serviceweb.pages.SignIn;
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
    LoginPage loginPage;
    SignIn signIn;
    public ServiceWebManager(WebDriver driver, SWEnv env){
        this(driver, env.toString());
        loginPage  = new LoginPage(driver);
        signIn = new SignIn(driver);

    }

    public ServiceWebManager(WebDriver driver, String env){
        this.driver = driver;
        this.swEnv = env;
    }

    public ServiceWebManager login(RCAccount account){
        LOGGER.info(String.format("[ServiceWebManager#login] %s", account));
        navigateTo(Navigation.LOGIN);
        signIn.setEmailOrPhoneNumber(account.getMainNumber());
        signIn.next();
        loginPage.setExtension(account.getExtension())
                .setPassword(account.getPassword())
                .submit();
        return this;
    }

    public void navigateTo(Navigation navigation) {
        driver.get(getEntry(navigation));
    }

    private String getEntry(Navigation navigation){
        return this.swEnv+ navigation.toString();
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public SignIn getSignIn() {
        return signIn;
    }
}
