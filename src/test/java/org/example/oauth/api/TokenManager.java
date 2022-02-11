package org.example.oauth.api;

import io.restassured.response.Response;
import org.example.oauth.constants.StatusCode;
import org.example.oauth.utils.ConfigLoader;
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

    public static synchronized String getToken() {
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
        formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
        formParams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
        formParams.put("client_id", ConfigLoader.getInstance().getClientId());
        formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());

        Response response = postAccount(formParams);

        if(response.statusCode() != StatusCode.CODE_200.getCode()) {
            throw new RuntimeException("Failed fetching renew token, no point proceeding further!");
        }

        return response;
    }
}
