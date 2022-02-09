package org.example.oauth.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.example.oauth.api.SpecBuilder.*;

public class MethodBuilder {

    public static Response get(String path, String token) {
        return given(getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .when()
                .get(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response post(String path, String token, Object request) {
        return given(getRequestSpec())
                .header("Authorization", "Bearer " + token)
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
                .header("Authorization", "Bearer " + token)
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
                .post("/api/token")
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }
}
