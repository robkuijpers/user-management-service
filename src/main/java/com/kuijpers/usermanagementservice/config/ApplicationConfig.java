package com.kuijpers.usermanagementservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("application")
public class ApplicationConfig {

    /**
     * Logging
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);

    /**
     * Name of application
     */
    private String name;


    /**
     *
     * Return the application name from application.yml
     *
     * @return String
     */
    public String getName() {
        return this.name;
    }

}
