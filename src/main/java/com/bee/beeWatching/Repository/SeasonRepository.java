package com.bee.beeWatching.Repository;


import com.bee.beeWatching.Model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SeasonRepository extends JpaRepository<Season,Integer> {

    @Query(value = "SELECT * FROM season s WHERE s.date_start <= CURRENT_TIMESTAMP  AND s.date_end >= CURRENT_TIMESTAMP\n",nativeQuery = true)
    public Season findCurrentSeason();
}
