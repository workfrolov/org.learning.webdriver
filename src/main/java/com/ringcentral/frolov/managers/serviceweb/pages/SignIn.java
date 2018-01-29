package com.ringcentral.frolov.managers.serviceweb.pages;


import com.ringcentral.frolov.managers.serviceweb.elements.TextInput;
import com.ringcentral.frolov.managers.serviceweb.elements.impl.TextInputImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;


/**
 * Created by alexanderzaverukha on 11/19/17.
 */
public class SignIn extends BasePage {
    TextInput emailOrPhoneNumberInput;
    public SignIn(){
        emailOrPhoneNumberInput   = new TextInputImpl("EmailOrPhoneNumber", "//*[@id='credential']");

    }


    @Step("Set email or phonenumber: {0}")
    public SignIn setEmailOrPhoneNumber(String emailOrPhoneNumber) {
        emailOrPhoneNumberInput.setValue(emailOrPhoneNumber);
        return this;
    }

    @Step("Click 'Next'")
    public void next() {
        WebElement nextButton = getDriver().findElement(By.xpath("//button[@data-test-automation-id='loginCredentialNext']"));
        nextButton.click();
    }
}
