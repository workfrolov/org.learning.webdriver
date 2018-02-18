package com.ringcentral.frolov.managers.serviceweb.pages;

import com.ringcentral.frolov.managers.serviceweb.elements.FooterElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.*;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

/**
 * Created by alexanderzaverukha on 11/19/17.
 */
public class MainPage extends BasePage {

    private FooterElements footerElements;

    public String checkFooterLegalLink(){
        return footerElements.checkLegal();
    }


    @Step("Get login number")
    public String getLoginNumber() {
        return getDriver().findElement(By.xpath("//div[@id='rc-login-country-number']//input")).getAttribute("value");
    }

    @Step("Get account info")
    public String getAccountInfo() {
        return getDriver().findElement(By.cssSelector("span.extension-info")).getText();
    }
}
