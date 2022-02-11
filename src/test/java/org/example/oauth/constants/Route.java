package org.example.oauth.constants;

import org.jetbrains.annotations.Contract;

public enum Route {
    BASE_PATH("/v1"),
    API("/api"),
    TOKEN("/token"),
    USERS("/users"),
    PLAYLISTS("/playlists");

    private final String url;

    @Contract(pure = true)
    public String getUrl() {
        return url;
    }

    @Contract(pure = true)
    Route(String url) { this.url = url; }
}
