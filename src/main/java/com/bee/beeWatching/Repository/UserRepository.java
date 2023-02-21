package com.bee.beeWatching.Repository;

import com.bee.beeWatching.Model.User;
import com.bee.beeWatching.Repository.Base.GenericRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT t FROM User t where t.username = :username")
    public User findByUsername(@Param("username") String username);
}