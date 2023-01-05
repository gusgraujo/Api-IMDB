package com.bee.beeWatching.Service.Impl;

import com.bee.beeWatching.Exception.ResourceNotFoundException;
import com.bee.beeWatching.Model.Movie;
import com.bee.beeWatching.Repository.MovieRepository;
import com.bee.beeWatching.Service.MovieService;
import com.bee.beeWatching.Utils.RequestApiMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    RequestApiMovie requestUtil = new RequestApiMovie();

    @Override
    public String getMovieTitleById(String idMovie) throws IOException {
        return requestUtil.findMovieTitleById(idMovie);
    }

    @Override
    public Movie getMovieById(String idMovie) throws ResourceNotFoundException {
        Movie movie = null;
        try{
            movie = requestUtil.formatMovie(idMovie);
            System.out.println(movie);
        }catch (Exception e){
            throw new ResourceNotFoundException("Movie not found with ID: " + idMovie);
        }
        return movie;
    }

    @Override
    public Movie save(Movie newMovie) {
        return movieRepository.save(newMovie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> getMovieSaved(String idMovie) {
        return movieRepository.findById(idMovie);
    }

    @Override
    public void deleteMovieSaved(Movie movie) {
        movieRepository.delete(movie);
    }

    @Override
    public Movie getMovieByTitle(String title) throws IOException {
        return requestUtil.formatMovie(requestUtil.findMovieByTitle(title)) ;
    }


}
