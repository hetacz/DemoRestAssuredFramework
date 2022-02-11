package org.example.oauth.tests;

import io.restassured.response.Response;
import org.example.oauth.api.app.PlaylistApi;
import org.example.oauth.constants.StatusCode;
import org.example.oauth.pojo.error.ErrorRoot;
import org.example.oauth.pojo.playlist.Playlist;
import org.example.oauth.utils.DataLoader;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Test;

import static org.example.oauth.utils.FakerUtils.generateDescription;
import static org.example.oauth.utils.FakerUtils.generatePlaylistName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTest {

    @Test
    public void userShouldBeAbleToCreatePlaylist() {
        Playlist requestPlaylist = playlistBuilder(generatePlaylistName(), generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist);
        Playlist responsePlaylist = response.as(Playlist.class);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201);
        assertPlaylistEqual(requestPlaylist, responsePlaylist);
    }

    @Test
    public void userShouldBeAbleToGetPlaylist() {
        Playlist expectedPlaylist = playlistBuilder("New Playlist","New Playlist description", false);
        Response response = PlaylistApi.get(DataLoader.getInstance().getGetPlaylistId());
        Playlist responsePlaylist = response.as(Playlist.class);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
        assertPlaylistEqual(expectedPlaylist, responsePlaylist);
    }

    @Test
    public void userShouldBeAbleToUpdatePlaylist() {
        Playlist requestPlaylist = playlistBuilder(generatePlaylistName(), generateDescription(), false);
        Response response = PlaylistApi.put(requestPlaylist, DataLoader.getInstance().getPutPlaylistId());
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
    }

    @Test
    public void userShouldNotBeAbleToCreatePlaylistWithoutName() {
        Playlist requestPlaylist = playlistBuilder("", generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist);
        ErrorRoot errorRoot = response.as(ErrorRoot.class);
        assertStatusCode(response.statusCode(), StatusCode.CODE_400);
        assertError(errorRoot, StatusCode.CODE_400);
    }

    @Test
    public void userShouldNotBeAbleToCreatePlaylistWithNotValidToken() {
        Playlist requestPlaylist = playlistBuilder(generatePlaylistName(), generateDescription(), false);
        final String badToken = "1234567890";
        Response response = PlaylistApi.post(requestPlaylist, badToken);
        ErrorRoot errorRoot = response.as(ErrorRoot.class);
        assertStatusCode(response.statusCode(), StatusCode.CODE_401);
        assertError(errorRoot, StatusCode.CODE_401);
    }

    private Playlist playlistBuilder(String name, String description, boolean _public) {
        return Playlist.builder()
                .name(name)
                .description(description)
                ._public(_public)
                .build();
    }

    private void assertPlaylistEqual(@NotNull Playlist requestPlaylist, @NotNull Playlist responsePlaylist){
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
    }

    private void assertStatusCode(int actualStatusCode, @NotNull StatusCode statusCode) {
        assertThat(actualStatusCode, equalTo(statusCode.getCode()));
    }

    private void assertError(@NotNull ErrorRoot responseErr, @NotNull StatusCode statusCode) {
        assertThat(responseErr.getError().getStatus(), equalTo(statusCode.getCode()));
        assertThat(responseErr.getError().getMessage(), equalTo(statusCode.getMessage()));
    }
}
