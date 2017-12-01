package com.ringcentral.frolov;

/**
 * Created by alexanderzaverukha on 11/19/17.
 */
public class RCAccount {
    private String mainNumber;
    private String extension;
    private String password;

    public RCAccount(String mainNumber, String password) {
        this(mainNumber, password, "");
    }

    public RCAccount(String mainNumber, String password, String extension) {
        this.mainNumber = mainNumber;
        this.password = password;
        this.extension = extension;
    }


    public String getMainNumber() {
        return mainNumber;
    }


    public String getExtension() {
        return extension;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("MainNumber: %s, Extension: %s, Password: %s", mainNumber, extension, password);
    }
}
