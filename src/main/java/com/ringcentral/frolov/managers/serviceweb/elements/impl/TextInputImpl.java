package com.ringcentral.frolov.managers.serviceweb.elements.impl;

import com.ringcentral.frolov.core.WrappedDriver;
import com.ringcentral.frolov.managers.serviceweb.elements.BaseElement;
import com.ringcentral.frolov.managers.serviceweb.elements.TextInput;


/**
 * Created by alexanderzaverukha on 1/28/18.
 */
public class TextInputImpl extends BaseElementImlp implements TextInput{
    public TextInputImpl(String name, String locator) {
        super(name, locator);
    }

    public void setValue(String value){
        WrappedDriver.get().setValue(locator, value);

    }

    public String getValue(){
        return WrappedDriver.get().getValue(locator);
    }
}
