package com.bee.beeWatching;

import com.bee.beeWatching.Model.Movie;
import com.bee.beeWatching.Utils.RequestApiMovie;
import com.squareup.okhttp.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UtilsApiTest {
    RequestApiMovie req = new RequestApiMovie();
    @Test
    public void givenMovieTheExists_assertThatInformationIsRetrievedInFindMovieByTitleFunction() throws IOException {
        String title = req.findMovieTitleById("tt15398776");
        assertNotNull(title);
        assertEquals(title, "Oppenheimer");
    }
    @Test
    public void givenMovieTheExists_assertThatGenresIsRetrieved() throws IOException {
        String genres = req.getGenresById("tt15398776");
        System.out.println("Genres: " + genres);
    }
    @Test
    public void givenMovieNameSetMovie() throws IOException {
        //Response response = req.findMovieByTitle(req.findMovieTitleById("tt0499549"));
        Movie movie = req.formatMovie("tt0499549");
        System.out.println(movie);
    }
}
