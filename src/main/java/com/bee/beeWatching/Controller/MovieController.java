package com.bee.beeWatching.Controller;

import com.bee.beeWatching.Exception.ResourceNotFoundException;
import com.bee.beeWatching.Model.Movie;
import com.bee.beeWatching.Model.User;
import com.bee.beeWatching.Service.MovieService;
import com.bee.beeWatching.Service.SeasonService;
import com.bee.beeWatching.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    SeasonService seasonService;
    @Autowired
    UserService userService;

    @PostMapping("/select/{id}")
    public ResponseEntity<String> selectMovie(@PathVariable String id, @RequestParam(name = "userId") int userId) throws ResourceNotFoundException {
        if (seasonService.IsHappeningSeason()){
                Movie movieSelected = movieService.getMovieById(id);
                Optional<User> userSelected = userService.getUserById(userId);
                movieSelected.setUser(userSelected.get());
                movieSelected.setSeason(seasonService.getCurrentSeason());
                movieService.save(movieSelected);
                return new ResponseEntity<>(String.valueOf(movieSelected),HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Season isn't happening, contact the administrator",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable String id) throws ResourceNotFoundException {
        return new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
    }
    @GetMapping("/getName/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name) throws IOException {
        return new ResponseEntity<>(movieService.getMovieByTitle(name),HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }
    @DeleteMapping("/deleteSelected")
    @ResponseBody
    public ResponseEntity deleteMovieSelected(@RequestParam(name = "idMovie") String idMovie)
    {
        movieService.deleteMovieSaved(movieService.getMovieSaved(idMovie).get());
        return new ResponseEntity("Movie deleted with ID: "+ idMovie,HttpStatus.OK);
    }

    @PostMapping("/selectBackground/{id}")
    @ResponseBody
    public ResponseEntity setBackgroundMovie(@PathVariable String id, @RequestParam(name = "url") String url) throws ResourceNotFoundException
    {
        Optional<Movie> movieSelected = movieService.getMovieSaved(id);
        if (movieSelected.isPresent())
        {
            movieSelected.get().setBackground(url);
            movieService.save(movieSelected.get());
            return new ResponseEntity<>(movieSelected,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(movieSelected,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/currentSeason")
    public List<Movie> getMoviesCurrentSeason(){
        int currentSeason = seasonService.getCurrentSeason().getSeason();
        return movieService.getAllMovies().stream().filter( movie -> movie.getSeason().getSeason() == currentSeason).collect(Collectors.toList());
    }
    @GetMapping("/getSeasonSorted/{id}")
    public List<Movie> getMoviesCurrentSeason(@PathVariable int id){
        return movieService.getAllMovies().stream().filter( movie -> movie.getSeason().getSeason() == id).sorted((m1,m2) -> m1.getName().compareTo(m2.getName())).collect(Collectors.toList());
    }
    @GetMapping("/getSeason/{id}")
    public List<Movie> getMoviesBySeason(@PathVariable int id)
    {
        return movieService.getAllMovies().stream().filter( movie -> movie.getSeason().getSeason() == id).collect(Collectors.toList());
    }
}
