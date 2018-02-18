package com.ringcentral.frolov.managers.serviceweb.elements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.*;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.*;
/**
 * Created by user on 16.02.2018.
 */
@Name("Footer")
@Block(@FindBy(xpath = "//*[@id=\"rc-gen3\"]"))
public class FooterElements extends HtmlElement{
    @Name("Legal")
    @FindBy(xpath = "//*[@class='copyright']")
    private Link legalLink;

    public String checkLegal(){
       return legalLink.getText();
    }
}