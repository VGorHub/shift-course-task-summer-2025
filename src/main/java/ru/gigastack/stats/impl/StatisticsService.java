package ru.gigastack.stats.impl;

import lombok.Getter;
import ru.gigastack.cli.Params;
import ru.gigastack.enums.DataType;
import ru.gigastack.stats.StatsCollector;
import ru.gigastack.stats.StatsRezult;

import java.util.EnumMap;
import java.util.Map;

public class StatisticsService {
    @Getter
    private final Params.Statistic mode;

    private final Map<DataType, StatsCollector> collectorMap = new EnumMap<>(DataType.class);

    public StatisticsService(Params.Statistic mode) {
        this.mode = mode;
        collectorMap.put(DataType.INTEGER, new IntStatsCollector());
        collectorMap.put(DataType.FLOAT, new FloatStatsCollector());
        collectorMap.put(DataType.STRING, new StringStatsCollector());
    }

    public void onLine(DataType type,String line ){
        collectorMap.get(type).accept(line);
    }

    public Map<DataType, StatsRezult> rezultMap(){
        Map<DataType, StatsRezult> rez = new EnumMap<>(DataType.class);
        collectorMap.forEach((t , r ) -> rez.put(t , r.rezult()));
        return rez;
    }

    public boolean hasData(DataType type){
        return collectorMap.get(type).hasData();
    }

}
