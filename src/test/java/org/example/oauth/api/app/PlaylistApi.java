package org.example.oauth.api.app;

import io.restassured.response.Response;
import org.example.oauth.api.MethodBuilder;
import org.example.oauth.api.TokenManager;
import org.example.oauth.pojo.playlist.Playlist;

public class PlaylistApi {

    public static Response post(Playlist requestPlaylist) {
        return MethodBuilder.post("/users/hetacz/playlists", TokenManager.getToken(), requestPlaylist);
    }

    public static Response post(Playlist requestPlaylist, String token) {
        return MethodBuilder.post("/users/hetacz/playlists", token, requestPlaylist);
    }

    public static Response get(String playlistId) {
        return MethodBuilder.get("/playlists/" + playlistId, TokenManager.getToken());
    }

    public static Response put(Playlist requestPlaylist, String playlistId) {
        return MethodBuilder.put("/playlists/" + playlistId, TokenManager.getToken(), requestPlaylist);
    }
}
