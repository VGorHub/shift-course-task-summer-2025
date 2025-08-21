package ru.gigastack.stats.impl;

import org.junit.jupiter.api.Test;
import ru.gigastack.model.StatsRezult;

import static org.junit.jupiter.api.Assertions.*;

class StringStatsCollectorTest {

    @Test
    void countsAndTracksMinMaxLen() {
        StringStatsCollector c = new StringStatsCollector();

        c.accept("a");
        c.accept("bbb");
        c.accept("cc");

        StatsRezult r = c.rezult();
        assertEquals(3, r.count());
        assertEquals("minLen=1, maxLen=3", r.details());
    }
}
