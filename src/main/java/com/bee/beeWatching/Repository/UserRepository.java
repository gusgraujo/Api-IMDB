package com.bee.beeWatching.Repository;

import com.bee.beeWatching.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM  User u WHERE u.name LIKE :name%")
    public List<User> findUserByName(@Param("name") String name);

}