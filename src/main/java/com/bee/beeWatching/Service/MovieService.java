package com.bee.beeWatching.Service;

import com.bee.beeWatching.Exception.ResourceNotFoundException;
import com.bee.beeWatching.Model.Movie;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface MovieService {
    public String getMovieTitleById(String idMovie) throws IOException;

    public Movie getMovieById(String idMovie) throws ResourceNotFoundException;

    public Movie save(Movie newMovie);

    public List<Movie> getAllMovies();

    public Optional<Movie> getMovieSaved(String idMovie);

    public void deleteMovieSaved(Movie movie);

    public Movie getMovieByTitle(String title) throws IOException;
}
