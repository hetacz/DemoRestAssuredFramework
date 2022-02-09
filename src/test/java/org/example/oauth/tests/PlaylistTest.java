package org.example.oauth.tests;

import io.restassured.response.Response;
import org.example.oauth.api.app.PlaylistApi;
import org.example.oauth.pojo.error.ErrorRoot;
import org.example.oauth.pojo.playlist.Playlist;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTest {
    @Test
    public void userShouldBeAbleToCreatePlaylist() {
        /*
        Playlist responsePlaylist = given(getRequestSpec())
                .body(requestPlaylist)
                .when()
                .post("/users/hetacz/playlists")
                .then()
                .spec(getResponseSpec())
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract()
                .as(Playlist.class);
        */
        Playlist requestPlaylist = new Playlist()
                .withName("New Playlist")
                .withDescription("New Playlist description")
                .withPublic(false);

        Response response = PlaylistApi.post(requestPlaylist);
        Playlist responsePlaylist = response.as(Playlist.class);

        assertThat(response.statusCode(), equalTo(201));
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
    }

    @Test
    public void userShouldBeAbleToGetPlaylist() {
        Response response = PlaylistApi.get("3faT6EBV5urP3sXPm0RYUm");
        Playlist responsePlaylist = response.as(Playlist.class);

        assertThat(response.statusCode(), equalTo(200));
        assertThat(responsePlaylist.getName(), equalTo("Updated Playlist"));
        assertThat(responsePlaylist.getDescription(), equalTo("Updated Playlist description"));
        assertThat(responsePlaylist.getPublic(), equalTo(false));
    }

    @Test()
    public void userShouldBeAbleToUpdatePlaylist() {
        Playlist requestPlaylist = new Playlist()
                .withName("Updated Playlist")
                .withDescription("Updated Playlist description")
                .withPublic(false);

        Response response = PlaylistApi.put(requestPlaylist, "3faT6EBV5urP3sXPm0RYUm");

        assertThat(response.statusCode(), equalTo(200));
    }

    @Test
    public void userShouldNotBeAbleToCreatePlaylistWithoutName() {
        Playlist requestPlaylist = new Playlist()
                .withName("")
                .withDescription("New Playlist description")
                .withPublic(false);

        Response response = PlaylistApi.post(requestPlaylist);
        ErrorRoot errorRoot = response.as(ErrorRoot.class);

        assertThat(response.statusCode(), equalTo(400));
        assertThat(errorRoot.getError().getStatus(), equalTo(400));
        assertThat(errorRoot.getError().getMessage(), equalTo("Missing required field: name"));
    }

    @Test
    public void userShouldNotBeAbleToCreatePlaylistWithNotValidToken() {
        Playlist requestPlaylist = new Playlist()
                .withName("New Playlist")
                .withDescription("New Playlist description")
                .withPublic(false);

        final String badToken = "1234567890";

        Response response = PlaylistApi.post(requestPlaylist, badToken);
        ErrorRoot errorRoot = response.as(ErrorRoot.class);
        assertThat(response.statusCode(), equalTo(401));
        assertThat(errorRoot.getError().getStatus(), equalTo(401));
        assertThat(errorRoot.getError().getMessage(), equalTo("Invalid access token"));
    }
}
