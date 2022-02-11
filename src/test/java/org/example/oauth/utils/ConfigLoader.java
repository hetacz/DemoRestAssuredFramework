package org.example.oauth.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Properties;

public final class ConfigLoader {

    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){
        properties = PropertiesLoader.properties("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance() {
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    private @NotNull String getProperty(String propertyName) {
        final String property = properties.getProperty(propertyName);
        if (property != null) {
            return property;
        } else {
            throw new RuntimeException("Property " + propertyName + " is not specified in the config.properties file.");
        }
    }

    public @NotNull String getClientId() {
        return getProperty("client_id");
    }

    public @NotNull String getClientSecret() {
        return getProperty("client_secret");
    }

    public @NotNull String getRefreshToken() {
        return getProperty("refresh_token");
    }

    public @NotNull String getGrantType() {
        return getProperty("grant_type");
    }

    public @NotNull String getUserId() {
        return getProperty("user.id");
    }
}
