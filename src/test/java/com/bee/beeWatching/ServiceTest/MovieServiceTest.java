package com.bee.beeWatching.ServiceTest;


import com.bee.beeWatching.Exception.ResourceNotFoundException;
import com.bee.beeWatching.Model.Movie;
import com.bee.beeWatching.Service.MovieService;
import com.bee.beeWatching.Utils.RequestApiMovie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieServiceTest {


    @Autowired
    MovieService movieService;

    @Test
    public void givenAIdMovieRetrieveAObjectMovie() throws IOException, ResourceNotFoundException {
        String idMovie = "tt11564570";

        System.out.println(movieService.getMovieById(idMovie));
        //Movie movie = requestUtil.formatMovie("tt0499549");
        //System.out.println(movie);
    }

}
