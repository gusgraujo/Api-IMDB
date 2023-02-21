package com.bee.beeWatching.Service;

import com.bee.beeWatching.Exception.ResourceNotFoundException;
import com.bee.beeWatching.Model.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieService{

    public Movie getMovieById(String idMovie) throws IOException;

    public String getMovieTrailerById(String idMovie) throws IOException;

    public Movie getMovieByTitle(String title) throws IOException;

    Movie save(Movie entity);

    Movie findById(int id);

    List<Movie> findAll();

    Movie findCurrentMovie();

    void deleteById(int id);

    public Movie updateMovie(int id, Movie movie) throws ResourceNotFoundException;
}
