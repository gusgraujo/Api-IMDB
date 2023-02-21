package com.bee.beeWatching.Service.Impl;


import com.bee.beeWatching.Exception.ResourceNotFoundException;
import com.bee.beeWatching.Model.Season;
import com.bee.beeWatching.Repository.SeasonRepository;
import com.bee.beeWatching.Service.SeasonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SeasonServiceImpl  implements SeasonService {


    private static final Logger logger = LogManager.getLogger(SeasonServiceImpl.class);

    @Autowired
    SeasonRepository seasonRepository;

    @Override
    public Season getCurrentSeason()
    {
        return seasonRepository.getCurrentSeason();
    }

    @Override
    public boolean isBetweenSeason(Date dateStart, Date dateEnd) {
        return seasonRepository.isBetweenSeason(dateStart, dateEnd) != null;
    }

    @Override
    public Season updateSeason(int id, Season season) throws ResourceNotFoundException {
        Season existingSeason = seasonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Season not found with id: " + id));
        existingSeason.setName(season.getName());
        existingSeason.setDateStart(season.getDateStart());
        existingSeason.setDateEnd(season.getDateEnd());
        saveSeason(existingSeason);
        return seasonRepository.save(existingSeason);
    }

    @Override
    public List<Season> getSeasonByName(String name)
    {
        return seasonRepository.findByName(name);
    }

    @Override
    public Season saveSeason(Season entity) {
        try {
            logger.info("Season saveSeason INI");
            if (entity.getGuuid() == null) {
                entity.setGuuid(UUID.randomUUID());
            }
            if (entity.getCreatedAt() == null) {
                entity.setCreatedAt(new Date());
            }
            entity.setUpdatedAt(new Date());
            logger.info("Season saveSeason END");
            return seasonRepository.save(entity);
        } catch (Exception e) {
            logger.error("Error saving Season: " + e.getMessage(), e);
            throw new RuntimeException("Error saving Season", e);
        }
    }

    @Override
    public Season findById(int id) {
        Optional<Season> optionalEntity = seasonRepository.findById(id);
        return optionalEntity.orElse(null);
    }

    @Override
    public List<Season> findAll() {
        return seasonRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        seasonRepository.deleteById(id);
    }
}
