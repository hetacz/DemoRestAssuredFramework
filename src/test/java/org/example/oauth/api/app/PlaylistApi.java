package org.example.oauth.api.app;

import io.restassured.response.Response;
import org.example.oauth.api.MethodBuilder;
import org.example.oauth.api.TokenManager;
import org.example.oauth.constants.Route;
import org.example.oauth.pojo.playlist.Playlist;
import org.example.oauth.utils.ConfigLoader;

public class PlaylistApi {

    public static Response post(Playlist requestPlaylist) {
        return MethodBuilder.post(Route.USERS.getUrl() + "/" + ConfigLoader.getInstance().getUserId() + Route.PLAYLISTS.getUrl() + "/", TokenManager.getToken(), requestPlaylist);
    }

    public static Response post(Playlist requestPlaylist, String token) {
        return MethodBuilder.post(Route.USERS.getUrl() + Route.PLAYLISTS.getUrl(), token, requestPlaylist);
    }

    public static Response get(String playlistId) {
        return MethodBuilder.get(Route.PLAYLISTS.getUrl() + "/" + playlistId, TokenManager.getToken());
    }

    public static Response put(Playlist requestPlaylist, String playlistId) {
        return MethodBuilder.put(Route.PLAYLISTS.getUrl() + "/" + playlistId, TokenManager.getToken(), requestPlaylist);
    }
}
