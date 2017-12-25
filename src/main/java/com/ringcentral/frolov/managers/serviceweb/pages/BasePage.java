package com.ringcentral.frolov.managers.serviceweb.pages;

import com.ringcentral.frolov.core.WrappedDriver;
import org.openqa.selenium.*;
import ru.yandex.qatools.allure.annotations.Attachment;


/**
 * Created by user on 30.11.2017.
 */
public class BasePage {

    protected WebDriver getDriver() {
        return WrappedDriver.get().getDriver();
    }

    @Attachment(value = "{0}", type = "image/png")
    public byte[] saveScreenshot(String name) {
        return ((TakesScreenshot) WrappedDriver.get().getDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
