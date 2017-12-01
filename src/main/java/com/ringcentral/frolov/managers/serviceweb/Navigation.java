package com.ringcentral.frolov.managers.serviceweb;

/**
 * Created by alexanderzaverukha on 11/19/17.
 */
public enum Navigation {
    LOGIN("/#/enterCredential");
    String navLink;

    Navigation(String navLink) {

        this.navLink = navLink;
    }

    @Override
    public String toString() {

        return navLink;
    }
}