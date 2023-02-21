package com.bee.beeWatching.Service;

import com.bee.beeWatching.Exception.ResourceNotFoundException;
import com.bee.beeWatching.Model.Season;

import java.util.Date;
import java.util.List;

public interface SeasonService{

    public Season getCurrentSeason();

    public boolean isBetweenSeason(Date dateStart, Date dateEnd);

    public Season updateSeason(int id, Season season) throws ResourceNotFoundException;

    public List<Season> getSeasonByName(String name);
    Season saveSeason(Season entity);

    Season findById(int id);

    List<Season> findAll();

    void deleteById(int id);
}
