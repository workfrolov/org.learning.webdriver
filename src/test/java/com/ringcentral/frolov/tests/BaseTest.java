package com.ringcentral.frolov.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ringcentral.frolov.core.Config;
import com.ringcentral.frolov.managers.serviceweb.ServiceWebManager;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    ServiceWebManager serviceWebManager;

    @BeforeClass
    public void setUp() throws URISyntaxException, IOException {


    }

    @AfterClass
    public void tearDown() {

    }


}