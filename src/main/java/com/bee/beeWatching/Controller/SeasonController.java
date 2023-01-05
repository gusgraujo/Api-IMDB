package com.bee.beeWatching.Controller;

import com.bee.beeWatching.Exception.ResourceNotFoundException;
import com.bee.beeWatching.Model.Season;
import com.bee.beeWatching.Service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/season")
public class SeasonController {

    @Autowired
    SeasonService seasonService;

    @GetMapping("/getCurrent")
    public Season getCurrentSeason()
    {
        return seasonService.getCurrentSeason();
    }

    @PostMapping("/create")
    public ResponseEntity<Season> createSeason(@RequestBody @Validated Season newSeason) throws Exception
    {
        return new ResponseEntity<>(seasonService.createSeason(newSeason), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<Season> getAllSeasons()
    {
        return seasonService.getAllSeasons();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Season> updateSeason(@PathVariable int id,@RequestBody @Validated Season newSeason) throws ResourceNotFoundException {
        Optional<Season> season = seasonService.getSeasonById(id);
        if (season.isPresent()){
            season.get().setName(newSeason.getName());
            season.get().setDateStart(newSeason.getDateStart());
            season.get().setDateEnd(newSeason.getDateEnd());
            seasonService.saveSeason(season.get());
        }
        return new ResponseEntity<>(season.get(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSeason(@PathVariable int id) throws ResourceNotFoundException {
        seasonService.deleteSeason(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
