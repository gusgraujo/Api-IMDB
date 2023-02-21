package com.bee.beeWatching.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ParticipantTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link Participant}
     *   <li>{@link Participant#setAvatarFileUpload(String)}
     *   <li>{@link Participant#setDiscordName(String)}
     *   <li>{@link Participant#getAvatarFileUpload()}
     *   <li>{@link Participant#getDiscordName()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Participant actualParticipant = new Participant();
        actualParticipant.setAvatarFileUpload("Avatar File Upload");
        actualParticipant.setDiscordName("Discord Name");
        assertEquals("Avatar File Upload", actualParticipant.getAvatarFileUpload());
        assertEquals("Discord Name", actualParticipant.getDiscordName());
    }
}

