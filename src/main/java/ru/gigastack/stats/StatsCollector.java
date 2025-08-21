package ru.gigastack.stats;

public interface StatsCollector {
    void accept(String line);
    StatsRezult rezult();
}
