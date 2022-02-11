package org.example.oauth.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Properties;

public final class DataLoader {

    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader(){
        properties = PropertiesLoader.properties("src/test/resources/data.properties");
    }

    public static DataLoader getInstance() {
        if(dataLoader == null){
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    private @NotNull String getProperty(String propertyName) {
        final String property = properties.getProperty(propertyName);
        if (property != null) {
            return property;
        } else {
            throw new RuntimeException("Property " + propertyName + " is not specified in the config.properties file.");
        }
    }

    public @NotNull String getGetPlaylistId() {
        return getProperty("get_playlist_id");
    }

    public @NotNull String getPutPlaylistId() {
        return getProperty("put_playlist_id");
    }
}
