package com.bee.beeWatching.Repository.Base;


import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
@Primary
public interface GenericRepository<T, ID> extends JpaRepository<T, ID> {

    @Query("SELECT t FROM #{#entityName} t WHERE t.name = ?1")
    List<T> findByName(String name);

}