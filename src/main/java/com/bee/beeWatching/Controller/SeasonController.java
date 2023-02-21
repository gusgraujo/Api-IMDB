package com.bee.beeWatching.Controller;

import com.bee.beeWatching.Exception.ResourceNotFoundException;
import com.bee.beeWatching.Model.Season;
import com.bee.beeWatching.Service.Impl.SeasonServiceImpl;
import com.bee.beeWatching.Service.SeasonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/season")
@CrossOrigin
public class SeasonController {

    private static final Logger logger = LogManager.getLogger(SeasonController.class);

    @Autowired
    SeasonService seasonService;

    @GetMapping("/getCurrent")
    public ResponseEntity<Season> getCurrentSeason() {
        try {
            Season current = seasonService.getCurrentSeason();
            logger.info("Successfully retrieved current season: " + current);
            return new ResponseEntity<>(current, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while retrieving current season: " + e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Season>> getSeasonByName(@PathVariable String name) {
        try {
            List<Season> seasons = seasonService.getSeasonByName(name);
            if (seasons.isEmpty()) {
                logger.warn("No seasons found with name: {}", name);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            logger.info("Returning {} seasons with name: {}", seasons.size(), name);
            return new ResponseEntity<>(seasons, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred while trying to get seasons by name: {}", name, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Season> createSeason(@RequestBody @Validated Season newSeason) throws Exception
    {
        try {
            if (seasonService.isBetweenSeason(newSeason.getDateStart(), newSeason.getDateEnd())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                Season savedSeason = seasonService.saveSeason(newSeason);
                logger.info("ResponseEntity<Season> createSeason else END");
                return new ResponseEntity<>(savedSeason, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            logger.error("Error creating season: " + e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Season>> getAllSeasons()
    {
        try {
            List<Season> seasons = seasonService.findAll();
            if (seasons.isEmpty()) {
                logger.info("No season created!");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(seasons, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while fetching all seasons: " + e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Season> updateSeason(@PathVariable int id,@RequestBody @Validated Season newSeason) throws ResourceNotFoundException {
        try {
            Season updatedSeason = seasonService.updateSeason(id, newSeason);
            logger.info("Season with id {} updated successfully", id);
            return new ResponseEntity<>(updatedSeason, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            logger.error("Season with id {} not found", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error occurred while updating season with id {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSeason(@PathVariable int id){
        try {
            Season season = seasonService.findById(id);
            if (season == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            seasonService.deleteById(id);
            logger.info("Season with id {} has been deleted", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while trying to delete season with id {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
