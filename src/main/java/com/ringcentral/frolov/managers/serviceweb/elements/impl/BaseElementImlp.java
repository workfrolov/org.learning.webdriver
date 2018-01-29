package com.ringcentral.frolov.managers.serviceweb.elements.impl;

import com.ringcentral.frolov.core.WrappedDriver;

/**
 * Created by alexanderzaverukha on 1/28/18.
 */
public class BaseElementImlp implements com.ringcentral.frolov.managers.serviceweb.elements.BaseElement {
    String name;
    String locator;
    public BaseElementImlp(String name, String locator){
        this.name = name;
        this.locator = locator;
    }

    @Override
    public String getLocator() {
        return this.locator;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void click() {
        WrappedDriver.get().click(this.locator);
    }

    @Override
    public void waitFor() {

    }

    @Override
    public void isVisible() {

    }

    @Override
    public void isPresent() {

    }
}
