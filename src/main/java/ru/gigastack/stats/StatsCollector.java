package ru.gigastack.stats;

import ru.gigastack.model.StatsResult;

public interface StatsCollector {
    void accept(String line);
    StatsResult result();
}
