package com.bee.beeWatching.Repository;

import com.bee.beeWatching.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,String> {
}
