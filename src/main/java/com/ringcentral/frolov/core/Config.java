package com.ringcentral.frolov.core;

/**
 * Created by alexanderzaverukha on 12/2/17.
 */
public class Config {
    private String driverPath;

    public String getServiceWebUrl() {
        return serviceWebUrl;
    }

    public void setServiceWebUrl(String serviceWebUrl) {
        this.serviceWebUrl = serviceWebUrl;
    }

    private String serviceWebUrl;

    public String getDriverPath() {
        return driverPath;
    }

    public void setDriverPath(String driverPath) {
        this.driverPath = driverPath;
    }

    public String getBrowserType() {
        return browserType;
    }

    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

    private String browserType;

}
