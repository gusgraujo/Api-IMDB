package com.bee.beeWatching.Controller;

import com.bee.beeWatching.Exception.ResourceNotFoundException;
import com.bee.beeWatching.Model.Movie;
import com.bee.beeWatching.Model.Participant;
import com.bee.beeWatching.Model.Season;
import com.bee.beeWatching.Service.MovieService;
import com.bee.beeWatching.Service.ParticipantService;
import com.bee.beeWatching.Service.SeasonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
@CrossOrigin
public class MovieController {

    private static final Logger logger = LogManager.getLogger(MovieController.class);
    @Autowired
    MovieService movieService;

    @Autowired
    SeasonService seasonService;
    @Autowired
    ParticipantService participantService;

    @PostMapping("/select")
    public ResponseEntity<Movie> selectMovie(@RequestParam(name = "IMDBId") String id, @RequestParam(name = "participantId") int participantId) throws IOException {
        try {
            logger.info(String.format("ResponseEntity<Movie> selectMovie INI"));
            logger.info(String.format("getCurrentSeason: %s", seasonService.getCurrentSeason() != null));
            if (seasonService.getCurrentSeason() != null) {
                Participant participantSelected = participantService.findById(participantId);
                logger.info(String.format("participantSelected: %d", participantSelected.getId()));
                if (participantSelected == null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                } else {
                    List<Participant> participants = new ArrayList<>();
                    Movie movieSelected = movieService.getMovieById(id);
                    if (movieSelected == null) {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    } else {
                        List<Season> seasons = new ArrayList<>();
                        participants.add(participantSelected);
                        movieSelected.setParticipants(participants);
                        seasons.add(seasonService.getCurrentSeason());
                        movieSelected.setSeason(seasons);
                        movieSelected.setTrailerUrl(movieService.getMovieTrailerById(id));
                        movieService.save(movieSelected);
                        logger.info(String.format("ResponseEntity<Movie> selectMovie END"));
                        return new ResponseEntity<>(movieSelected, HttpStatus.OK);
                    }
                }
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.info(String.format("ResponseEntity<Movie> selectMovie Exception"));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable String id) throws IOException {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/title/{title}")
    public ResponseEntity<Movie> getMovieByTitle(@PathVariable String title) throws IOException {
        Movie movie = movieService.getMovieByTitle(title);
        if (movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.findAll();
        if (!movies.isEmpty()) {
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/getCurrentMovie")
    public ResponseEntity<Movie> getCurrentMovie() {
        try {
            logger.info("ResponseEntity<Movie> getCurrentMovie INI");
            Movie movie = movieService.findCurrentMovie();
            if (movie != null) {
                logger.info("ResponseEntity<Movie> getCurrentMovie success END");
                return new ResponseEntity<>(movie, HttpStatus.OK);
            } else {
                logger.info("ResponseEntity<Movie> getCurrentMovie NO_CONTENT END");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e){
            logger.info(String.format("ResponseEntity<Movie> getCurrentMovie() Exception"));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Movie> deleteMovieSaved(@PathVariable int id)
    {
        Movie movie = movieService.findById(id);
        if (movie != null){
            movieService.deleteById(id);
            return new ResponseEntity<>(movie,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/selectBackground/{id}")
    @ResponseBody
    public ResponseEntity<Movie> setBackgroundMovie(@PathVariable int id, @RequestParam(name = "url") String url)
    {
        Movie movieSelected = movieService.findById(id);
        if (movieSelected != null)
        {
            movieSelected.setBackground(url);
            movieSelected.setUpdatedAt(new Date());
            movieService.save(movieSelected);
            return new ResponseEntity<>(movieSelected,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/currentSeason")
    public List<Movie> getMoviesCurrentSeason(){
        long currentSeason = seasonService.getCurrentSeason().getId();
        return movieService.findAll().stream().filter( movie -> movie.getSeason().get(0).getId() == currentSeason).collect(Collectors.toList());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable int id, @Validated @RequestBody Movie movie) throws ResourceNotFoundException {
        try {
            Movie updatedMovie = movieService.updateMovie(id, movie);
            if (updatedMovie != null) {
                logger.info("Movie with id {} updated successfully", id);
                return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
            } else {
                logger.error("Movie with id {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error while updating Movie with id {}: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
