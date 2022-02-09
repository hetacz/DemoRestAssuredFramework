package org.example.oauth.api;

import io.restassured.response.Response;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.HashMap;

import static org.example.oauth.api.MethodBuilder.postAccount;

public class TokenManager {

    private static String access_token;
    private static Instant expiry_time;
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenManager.class);

    public static String getToken() {
        try {
            if(access_token == null || Instant.now().isAfter(expiry_time)) {
                LOGGER.info("Renewing token...");
                Response response = renewToken();
                access_token = response.path("access_token");
                final int expiryDurationInSeconds = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300); // some time buffer, just to be safe
            } else {
                LOGGER.info("Token is good to use");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed fetching renew token, no point proceeding further!");
        }
        return access_token;
    }

    private static @NotNull Response renewToken() {
        HashMap<String, String> formParams = new HashMap<String, String>();
        formParams.put("grant_type", "refresh_token");
        formParams.put("refresh_token", "");
        formParams.put("client_id", "");
        formParams.put("client_secret", "");

        Response response = postAccount(formParams);

        if(response.statusCode() != 200) {
            throw new RuntimeException("Failed fetching renew token, no point proceeding further!");
        }

        return response;
    }
}
