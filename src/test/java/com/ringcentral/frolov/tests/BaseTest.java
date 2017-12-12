package com.ringcentral.frolov.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ringcentral.frolov.core.Config;
import com.ringcentral.frolov.managers.serviceweb.ServiceWebManager;
import org.openqa.selenium.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class BaseTest {
    static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    private Config config;
    ServiceWebManager serviceWebManager;

    @BeforeSuite
    public void beforeSuite() {
        initConfig();

    }

    @BeforeTest
    public void beforeTest() {
        serviceWebManager = new ServiceWebManager(config);
    }

    @AfterTest
    public void after() {
        if (serviceWebManager != null)
            serviceWebManager.stop();
    }

    public ServiceWebManager getServiceWebManager() {
        return this.serviceWebManager;
    }


    @BeforeClass
    public void setUp() throws URISyntaxException, IOException {


    }

    @AfterClass
    public void tearDown() {

    }

    public Config getConfig() {
        return this.config;
    }


    private void initConfig() {

        ClassLoader classLoader = getClass().getClassLoader();
        URL configUrl = classLoader.getResource("config.properties");
        URI uri = null;
        if (configUrl != null) {
            try {
                uri = configUrl.toURI();
            } catch (URISyntaxException e) {
                LOGGER.error("Init config", e);
            }

            try {
                this.config = initConfigFromProperties(configUrl.toURI());
            } catch (URISyntaxException e) {
                LOGGER.error("Init config", e);
            }
        } else {
            configUrl = classLoader.getResource("config.json");
            if (configUrl != null) {
                try {
                    uri = configUrl.toURI();
                } catch (URISyntaxException e) {
                    LOGGER.error("Init config", e);
                }
                if (Files.exists(Paths.get(uri))) {
                    this.config = initConfigFromJson(uri);
                }
            }
        }

        if (config == null) {
            throw new NotFoundException("Config file 'config.properties' or 'config.json'not found");
        }
    }

    private Config initConfigFromProperties(URI configUri) {
        Config config = new Config();
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(configUri));
            Properties properties = new Properties();
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            LOGGER.error("Init config from properties", e);
        } catch (IOException e) {
            LOGGER.error("Init config from properties", e);
        }

        //TODO read property and fill in config object
        return config;
    }

    private Config initConfigFromJson(URI configUri) {
        Config config = null;
        byte[] jsonData = new byte[0];
        try {
            jsonData = Files.readAllBytes(Paths.get(configUri));
        } catch (IOException e) {
            LOGGER.error("Init config from JSON", e);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            config = objectMapper.readValue(jsonData, Config.class);
        } catch (IOException e) {
            LOGGER.error("Init config from JSON", e);
        }
        return config;
    }


}