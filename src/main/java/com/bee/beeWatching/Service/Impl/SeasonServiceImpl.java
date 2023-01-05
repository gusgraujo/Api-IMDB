package com.bee.beeWatching.Service.Impl;


import com.bee.beeWatching.Exception.ResourceNotFoundException;
import com.bee.beeWatching.Model.Season;
import com.bee.beeWatching.Repository.SeasonRepository;
import com.bee.beeWatching.Service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SeasonServiceImpl implements SeasonService {

    @Autowired
    SeasonRepository seasonRepository;

    @Override
    public Season getCurrentSeason()
    {
        return seasonRepository.findCurrentSeason();
    }

    @Override
    public boolean IsHappeningSeason()
    {
        return seasonRepository.findCurrentSeason() != null;
    }

    @Override
    public Season createSeason(Season newSeason) throws Exception
    {
        Date now = new Date();
        if (newSeason.getDateEnd().compareTo(now) > 0 || newSeason.getDateEnd().compareTo(now) == 0){
            return seasonRepository.save(newSeason);
        }else {
            throw new Exception("Date invalid");
        }
    }

    @Override
    public List<Season> getAllSeasons()
    {
        return seasonRepository.findAll();
    }

    public Optional<Season> getSeasonById(int id) throws ResourceNotFoundException {
        try {
            return seasonRepository.findById(id);
        } catch (Exception e){
            throw new ResourceNotFoundException("Can not find season with ID :"+ id);
        }
    }

    @Override
    public Season saveSeason(Season newSeason) {
        return seasonRepository.save(newSeason);
    }

    @Override
    public void deleteSeason(int id) throws ResourceNotFoundException {
        try {
            seasonRepository.deleteById(id);
        }catch (Exception e){
            throw  new ResourceNotFoundException("Can not find the season");
        }
    }


}
