package com.bee.beeWatching.Repository;


import com.bee.beeWatching.Model.Season;
import com.bee.beeWatching.Repository.Base.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SeasonRepository extends GenericRepository<Season,Integer> {

    @Query(value = "SELECT * FROM season s WHERE s.date_start <= CURRENT_TIMESTAMP  AND s.date_end >= CURRENT_TIMESTAMP\n",nativeQuery = true)
    public Season getCurrentSeason();

    @Query(value = "SELECT * FROM season s WHERE :dateStart BETWEEN s.date_start AND s.date_end " +
            "or :dateEnd BETWEEN s.date_start  and  s.date_end LIMIT 1\n",nativeQuery = true)
    public Season isBetweenSeason(@Param("dateStart") Date dateStart,@Param("dateEnd") Date dateEnd);
}
