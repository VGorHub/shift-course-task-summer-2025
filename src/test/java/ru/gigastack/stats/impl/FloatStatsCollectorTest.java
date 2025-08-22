package ru.gigastack.stats.impl;

import org.junit.jupiter.api.Test;
import ru.gigastack.model.StatsResult;

import static org.junit.jupiter.api.Assertions.*;

class FloatStatsCollectorTest {

    @Test
    void collectsMinMaxSumAvgAndCount() {
        FloatStatsCollector c = new FloatStatsCollector();

        c.accept("1.0");
        c.accept("2.0");
        c.accept("3.0");

        StatsResult r = c.result();
        assertEquals(3, r.count());

        String d = r.details();
        assertTrue(d.contains("min=1.0"));
        assertTrue(d.contains("max=3.0"));
        assertTrue(d.contains("sum=6.0"));
        assertTrue(d.contains("avg=2"));
        assertFalse(d.contains("avg=2.0"));
    }
}
