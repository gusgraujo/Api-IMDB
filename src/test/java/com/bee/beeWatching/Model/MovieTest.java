package com.bee.beeWatching.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class MovieTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Movie#Movie()}
     *   <li>{@link Movie#setBackground(String)}
     *   <li>{@link Movie#setCategory(String)}
     *   <li>{@link Movie#setDuration(Double)}
     *   <li>{@link Movie#setIdMovie(String)}
     *   <li>{@link Movie#setImageURL(String)}
     *   <li>{@link Movie#setName(String)}
     *   <li>{@link Movie#setParticipants(List)}
     *   <li>{@link Movie#setRateIMDB(Double)}
     *   <li>{@link Movie#setRateMeta(Double)}
     *   <li>{@link Movie#setSeason(List)}
     *   <li>{@link Movie#setStatus(Boolean)}
     *   <li>{@link Movie#setTrailerUrl(String)}
     *   <li>{@link Movie#setYear(Integer)}
     *   <li>{@link Movie#toString()}
     *   <li>{@link Movie#getBackground()}
     *   <li>{@link Movie#getCategory()}
     *   <li>{@link Movie#getDuration()}
     *   <li>{@link Movie#getIdMovie()}
     *   <li>{@link Movie#getImageURL()}
     *   <li>{@link Movie#getName()}
     *   <li>{@link Movie#getParticipants()}
     *   <li>{@link Movie#getSeason()}
     *   <li>{@link Movie#getRateIMDB()}
     *   <li>{@link Movie#getRateMeta()}
     *   <li>{@link Movie#getStatus()}
     *   <li>{@link Movie#getTrailerUrl()}
     *   <li>{@link Movie#getYear()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Movie actualMovie = new Movie();
        actualMovie.setBackground("Background");
        actualMovie.setCategory("Category");
        actualMovie.setDuration(10.0d);
        actualMovie.setIdMovie("Id Movie");
        actualMovie.setImageURL("https://example.org/example");
        actualMovie.setName("Name");
        ArrayList<Participant> participantList = new ArrayList<>();
        actualMovie.setParticipants(participantList);
        actualMovie.setRateIMDB(10.0d);
        actualMovie.setRateMeta(10.0d);
        ArrayList<Season> seasonList = new ArrayList<>();
        actualMovie.setSeason(seasonList);
        actualMovie.setStatus(true);
        actualMovie.setTrailerUrl("https://example.org/example");
        actualMovie.setYear(1);
        String actualToStringResult = actualMovie.toString();
        assertEquals("Background", actualMovie.getBackground());
        assertEquals("Category", actualMovie.getCategory());
        assertEquals(10.0d, actualMovie.getDuration().doubleValue());
        assertEquals("Id Movie", actualMovie.getIdMovie());
        assertEquals("https://example.org/example", actualMovie.getImageURL());
        assertEquals("Name", actualMovie.getName());
        List<Participant> participants = actualMovie.getParticipants();
        assertSame(participantList, participants);
        List<Season> season = actualMovie.getSeason();
        assertEquals(season, participants);
        assertEquals(10.0d, actualMovie.getRateIMDB().doubleValue());
        assertEquals(10.0d, actualMovie.getRateMeta().doubleValue());
        assertSame(seasonList, season);
        assertEquals(participantList, season);
        assertTrue(actualMovie.getStatus());
        assertEquals("https://example.org/example", actualMovie.getTrailerUrl());
        assertEquals(1, actualMovie.getYear().intValue());
        assertEquals(
                "Movie{id='0'idMovie='Id Movie', year=1, background='Background', imageURL='https://example.org/example',"
                        + " category='Category', duration=10.0, rateIMDB=10.0, rateMeta=10.0, trailerUrl='https://example.org/example',"
                        + " status=true, seasons=[]}",
                actualToStringResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Movie#Movie(String, String, Integer, String, String, String, String, Double, Double, Double, List, List, Boolean)}
     *   <li>{@link Movie#setBackground(String)}
     *   <li>{@link Movie#setCategory(String)}
     *   <li>{@link Movie#setDuration(Double)}
     *   <li>{@link Movie#setIdMovie(String)}
     *   <li>{@link Movie#setImageURL(String)}
     *   <li>{@link Movie#setName(String)}
     *   <li>{@link Movie#setParticipants(List)}
     *   <li>{@link Movie#setRateIMDB(Double)}
     *   <li>{@link Movie#setRateMeta(Double)}
     *   <li>{@link Movie#setSeason(List)}
     *   <li>{@link Movie#setStatus(Boolean)}
     *   <li>{@link Movie#setTrailerUrl(String)}
     *   <li>{@link Movie#setYear(Integer)}
     *   <li>{@link Movie#toString()}
     *   <li>{@link Movie#getBackground()}
     *   <li>{@link Movie#getCategory()}
     *   <li>{@link Movie#getDuration()}
     *   <li>{@link Movie#getIdMovie()}
     *   <li>{@link Movie#getImageURL()}
     *   <li>{@link Movie#getName()}
     *   <li>{@link Movie#getParticipants()}
     *   <li>{@link Movie#getSeason()}
     *   <li>{@link Movie#getRateIMDB()}
     *   <li>{@link Movie#getRateMeta()}
     *   <li>{@link Movie#getStatus()}
     *   <li>{@link Movie#getTrailerUrl()}
     *   <li>{@link Movie#getYear()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        ArrayList<Participant> participantList = new ArrayList<>();
        ArrayList<Season> seasonList = new ArrayList<>();
        Movie actualMovie = new Movie("Id Movie", "Name", 1, "Background", "https://example.org/example",
                "https://example.org/example", "Category", 10.0d, 10.0d, 10.0d, participantList, seasonList, true);
        actualMovie.setBackground("Background");
        actualMovie.setCategory("Category");
        actualMovie.setDuration(10.0d);
        actualMovie.setIdMovie("Id Movie");
        actualMovie.setImageURL("https://example.org/example");
        actualMovie.setName("Name");
        ArrayList<Participant> participantList1 = new ArrayList<>();
        actualMovie.setParticipants(participantList1);
        actualMovie.setRateIMDB(10.0d);
        actualMovie.setRateMeta(10.0d);
        ArrayList<Season> seasonList1 = new ArrayList<>();
        actualMovie.setSeason(seasonList1);
        actualMovie.setStatus(true);
        actualMovie.setTrailerUrl("https://example.org/example");
        actualMovie.setYear(1);
        String actualToStringResult = actualMovie.toString();
        assertEquals("Background", actualMovie.getBackground());
        assertEquals("Category", actualMovie.getCategory());
        assertEquals(10.0d, actualMovie.getDuration().doubleValue());
        assertEquals("Id Movie", actualMovie.getIdMovie());
        assertEquals("https://example.org/example", actualMovie.getImageURL());
        assertEquals("Name", actualMovie.getName());
        List<Participant> participants = actualMovie.getParticipants();
        assertSame(participantList1, participants);
        assertEquals(participantList, participants);
        assertEquals(seasonList, participants);
        List<Season> season = actualMovie.getSeason();
        assertEquals(season, participants);
        assertEquals(10.0d, actualMovie.getRateIMDB().doubleValue());
        assertEquals(10.0d, actualMovie.getRateMeta().doubleValue());
        assertSame(seasonList1, season);
        assertEquals(participantList, season);
        assertEquals(seasonList, season);
        assertEquals(participantList1, season);
        assertTrue(actualMovie.getStatus());
        assertEquals("https://example.org/example", actualMovie.getTrailerUrl());
        assertEquals(1, actualMovie.getYear().intValue());
        assertEquals(
                "Movie{id='0'idMovie='Id Movie', year=1, background='Background', imageURL='https://example.org/example',"
                        + " category='Category', duration=10.0, rateIMDB=10.0, rateMeta=10.0, trailerUrl='https://example.org/example',"
                        + " status=true, seasons=[]}",
                actualToStringResult);
    }
}

