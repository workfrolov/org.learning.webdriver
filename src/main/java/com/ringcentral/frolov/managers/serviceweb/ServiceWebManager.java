package com.ringcentral.frolov.managers.serviceweb;

import com.ringcentral.frolov.RCAccount;
import com.ringcentral.frolov.core.Config;
import com.ringcentral.frolov.core.ConfigProperties;
import com.ringcentral.frolov.core.WrappedDriver;
import com.ringcentral.frolov.managers.serviceweb.components.Navigation;
import com.ringcentral.frolov.managers.serviceweb.pages.LoginPage;
import com.ringcentral.frolov.managers.serviceweb.pages.MainPage;
import com.ringcentral.frolov.managers.serviceweb.pages.SignIn;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderzaverukha on 11/19/17.
 */
public class ServiceWebManager {
    static final Logger LOGGER = LoggerFactory.getLogger(ServiceWebManager.class);
    WebDriver driver;

    //******* PAGES
    String swEnv;
    LoginPage loginPage;
    SignIn signIn;
    MainPage mainPage;
    Config config;


    public ServiceWebManager(Config config){
        this.swEnv = ConfigProperties.getTestProperty("serviceWebUrlProperty");
        loginPage = new LoginPage();
        signIn = new SignIn();
        mainPage = new MainPage();

    }


    public ServiceWebManager login(RCAccount account) {
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
        getDriver().get(getEntry(navigation));
    }



    private String getEntry(Navigation navigation) {
        return this.swEnv + navigation.toString();
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public SignIn getSignIn() {
        return signIn;
    }

    public MainPage getMainPage() { return  mainPage; }

    public void stop(){
        WrappedDriver.get().stop();
    }

    public WebDriver getDriver(){
        return  WrappedDriver.get().getDriver();
    }


    public void clickThePhoneNumbersTab(){
        WrappedDriver.get().click("phoneNumbers");
    }


}
