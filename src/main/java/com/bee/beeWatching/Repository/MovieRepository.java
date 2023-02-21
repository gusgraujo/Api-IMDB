package com.bee.beeWatching.Repository;

import com.bee.beeWatching.Model.Movie;
import com.bee.beeWatching.Repository.Base.GenericRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("movieRepository")
public interface MovieRepository extends GenericRepository<Movie, Integer> {

    @Query(value = "SELECT * FROM movie WHERE status is true; ",nativeQuery = true)
    public Movie findCurrentMovie();
}
