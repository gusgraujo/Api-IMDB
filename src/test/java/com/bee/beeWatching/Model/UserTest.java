package com.bee.beeWatching.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link User}
     *   <li>{@link User#setPassword(String)}
     *   <li>{@link User#setUsername(String)}
     *   <li>{@link User#toString()}
     *   <li>{@link User#getPassword()}
     *   <li>{@link User#getUsername()}
     * </ul>
     */
    @Test
    void testConstructor() {
        User actualUser = new User();
        actualUser.setPassword("iloveyou");
        actualUser.setUsername("janedoe");
        String actualToStringResult = actualUser.toString();
        assertEquals("iloveyou", actualUser.getPassword());
        assertEquals("janedoe", actualUser.getUsername());
        assertEquals("User{username='janedoe', password='iloveyou', id=0}", actualToStringResult);
    }
}

