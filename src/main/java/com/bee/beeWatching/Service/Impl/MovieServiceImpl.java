package com.bee.beeWatching.Service.Impl;

import com.bee.beeWatching.Controller.MovieController;
import com.bee.beeWatching.Exception.ResourceNotFoundException;
import com.bee.beeWatching.Model.Movie;
import com.bee.beeWatching.Repository.MovieRepository;
import com.bee.beeWatching.Service.MovieService;
import com.bee.beeWatching.Utils.RequestApiMovie;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class MovieServiceImpl implements MovieService {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(MovieController.class);
    @Autowired
    MovieRepository movieRepository;

    RequestApiMovie requestUtil;

    public MovieServiceImpl() {
        requestUtil = new RequestApiMovie();
    }

    @Override
    public Movie getMovieById(String idMovie) throws IOException {
            return requestUtil.formatMovie(idMovie);
    }
    @Override
    public String getMovieTrailerById(String idMovie) throws IOException {
        return requestUtil.findMovieTrailerById(idMovie);
    }
    @Override
    public Movie getMovieByTitle(String title) throws IOException {
        return requestUtil.formatMovie(requestUtil.findMovieByTitle(title)) ;
    }

    @Override
    public Movie save(Movie entity)
    {
        try {
            if (entity.getGuuid() == null) {
                entity.setGuuid(UUID.randomUUID());
            }
            if (entity.getCreatedAt() == null) {
                entity.setCreatedAt(new Date());
            }
            entity.setUpdatedAt(new Date());
            return movieRepository.save(entity);
        } catch (Exception e) {
            logger.error("Error saving movie: " + e.getMessage(), e);
            throw new RuntimeException("Error saving movie", e);
        }

    }

    @Override
    public Movie findById(int id) {
        Optional<Movie> optionalEntity = movieRepository.findById(id);
        return optionalEntity.orElse(null);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findCurrentMovie() {
        return movieRepository.findCurrentMovie();
    }

    @Override
    public void deleteById(int id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Movie updateMovie(int id, Movie movie) throws ResourceNotFoundException {
        try{
            Movie existingMovie = movieRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
            return save(movie);
        }catch (ResourceNotFoundException e){
            logger.error("Movie not found with id: " + id);
            return null;
        }
    }
}
