package com.bee.beeWatching.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;

class SeasonTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Season#Season()}
     *   <li>{@link Season#setDateEnd(Date)}
     *   <li>{@link Season#setDateStart(Date)}
     *   <li>{@link Season#setName(String)}
     *   <li>{@link Season#toString()}
     *   <li>{@link Season#getDateEnd()}
     *   <li>{@link Season#getDateStart()}
     *   <li>{@link Season#getName()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Season actualSeason = new Season();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        actualSeason.setDateEnd(fromResult);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        actualSeason.setDateStart(fromResult1);
        actualSeason.setName("Name");
        actualSeason.toString();
        assertSame(fromResult, actualSeason.getDateEnd());
        assertSame(fromResult1, actualSeason.getDateStart());
        assertEquals("Name", actualSeason.getName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Season#Season(String, Date, Date)}
     *   <li>{@link Season#setDateEnd(Date)}
     *   <li>{@link Season#setDateStart(Date)}
     *   <li>{@link Season#setName(String)}
     *   <li>{@link Season#toString()}
     *   <li>{@link Season#getDateEnd()}
     *   <li>{@link Season#getDateStart()}
     *   <li>{@link Season#getName()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date dateStart = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Season actualSeason = new Season("Name", dateStart,
                Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        actualSeason.setDateEnd(fromResult);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        actualSeason.setDateStart(fromResult1);
        actualSeason.setName("Name");
        actualSeason.toString();
        assertSame(fromResult, actualSeason.getDateEnd());
        assertSame(fromResult1, actualSeason.getDateStart());
        assertEquals("Name", actualSeason.getName());
    }
}

