package ru.gigastack.stats.impl;

import org.junit.jupiter.api.Test;
import ru.gigastack.stats.StatsRezult;

import static org.junit.jupiter.api.Assertions.*;

class IntStatsCollectorTest {

    @Test
    void collectsMinMaxSumAvgAndCount() {
        IntStatsCollector c = new IntStatsCollector();

        c.accept("1");
        c.accept("2");
        c.accept("3");

        StatsRezult r = c.rezult();
        assertEquals(3, r.count());
        assertEquals("min=1, max=3, sum=6, avg=2", r.details());
    }
}
