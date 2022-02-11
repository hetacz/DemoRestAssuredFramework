package org.example.oauth.constants;

import org.jetbrains.annotations.Contract;

public enum StatusCode {

    CODE_200(200, ""),
    CODE_201(201, ""),
    CODE_400(400, "Missing required field: name"),
    CODE_401(401, "Invalid access token");

    @Contract(pure = true)
    public int getCode() {
        return code;
    }

    @Contract(pure = true)
    public String getMessage() {
        return message;
    }

    private final int code;
    private final String message;
    @Contract(pure = true)
    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
