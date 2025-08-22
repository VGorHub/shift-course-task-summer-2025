package ru.gigastack.stats.impl;

import org.junit.jupiter.api.Test;
import ru.gigastack.model.StatsResult;

import static org.junit.jupiter.api.Assertions.*;

class StringStatsCollectorTest {

    @Test
    void countsAndTracksMinMaxLen() {
        StringStatsCollector c = new StringStatsCollector();

        c.accept("a");
        c.accept("bbb");
        c.accept("cc");

        StatsResult r = c.result();
        assertEquals(3, r.count());
        assertEquals("minLen=1, maxLen=3", r.details());
    }
}
