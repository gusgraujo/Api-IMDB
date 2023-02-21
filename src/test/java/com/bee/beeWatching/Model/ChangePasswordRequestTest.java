package com.bee.beeWatching.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ChangePasswordRequestTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link ChangePasswordRequest}
     *   <li>{@link ChangePasswordRequest#setNewPassword(String)}
     *   <li>{@link ChangePasswordRequest#getNewPassword()}
     * </ul>
     */
    @Test
    void testConstructor() {
        ChangePasswordRequest actualChangePasswordRequest = new ChangePasswordRequest();
        actualChangePasswordRequest.setNewPassword("iloveyou");
        assertEquals("iloveyou", actualChangePasswordRequest.getNewPassword());
    }
}

