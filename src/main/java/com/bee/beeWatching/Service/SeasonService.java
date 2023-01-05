package com.bee.beeWatching.Service;

import com.bee.beeWatching.Exception.ResourceNotFoundException;
import com.bee.beeWatching.Model.Season;

import java.util.List;
import java.util.Optional;

public interface SeasonService {

    public Season getCurrentSeason();


    boolean IsHappeningSeason();

    public Season createSeason(Season newSeason) throws Exception;

    public List<Season> getAllSeasons();

    public Optional<Season> getSeasonById(int id) throws ResourceNotFoundException;

    public Season saveSeason(Season newSeason);

    public void deleteSeason(int id) throws ResourceNotFoundException;
}
