package com.ringcentral.frolov.managers.serviceweb;

/**
 * Created by alexanderzaverukha on 11/19/17.
 */
public enum SWEnv {
    AMS("https://service-amsup.lab.nordigy.ru");

    String url;

    SWEnv(String url) {

        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }
}