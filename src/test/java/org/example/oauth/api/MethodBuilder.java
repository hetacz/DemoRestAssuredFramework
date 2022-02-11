package org.example.oauth.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.oauth.constants.Route;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.example.oauth.api.SpecBuilder.*;

public class MethodBuilder {

    public static Response get(String path, String token) {
        return given(getRequestSpec())
                .auth()
                .oauth2(token)
                .when()
                .get(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response post(String path, String token, Object request) {
        return given(getRequestSpec())
                .auth()
                .oauth2(token)
                .body(request)
                .when()
                .post(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response put(String path, String token, Object request) {
        return given(getRequestSpec())
                .auth()
                .oauth2(token)
                .body(request)
                .when()
                .put(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response postAccount(HashMap<String, String> formParams) {
        return given(getAccountRequestSpec())
                .contentType(ContentType.URLENC)
                .formParams(formParams)
                .when()
                .post(Route.API.getUrl() + Route.TOKEN.getUrl())
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }
}
