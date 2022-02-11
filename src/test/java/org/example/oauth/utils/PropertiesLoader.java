package org.example.oauth.utils;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Properties;

public final class PropertiesLoader {

    public static @NotNull Properties properties(String filePath) {
        Properties properties = new Properties();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
                properties.load(reader);
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("Properties file not found at: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load properties file: " + filePath);
            }
        return properties;
    }
}
