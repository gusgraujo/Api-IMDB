package com.bee.beeWatching.Repository;

import com.bee.beeWatching.Model.Participant;
import com.bee.beeWatching.Repository.Base.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends GenericRepository<Participant, Integer> {
}
