package ru.gigastack.stats;

import ru.gigastack.model.StatsRezult;

public interface StatsCollector {
    void accept(String line);
    StatsRezult rezult();
}
