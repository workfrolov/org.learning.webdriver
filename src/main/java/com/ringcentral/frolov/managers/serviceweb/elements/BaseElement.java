package com.ringcentral.frolov.managers.serviceweb.elements;

/**
 * Created by alexanderzaverukha on 1/28/18.
 */
public interface BaseElement {
    String getLocator();
    String getName();
    void click();
    void waitFor();
    void isVisible();
    void isPresent();
}
