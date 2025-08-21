package ru.gigastack.stats;

import lombok.Getter;
import ru.gigastack.model.Params;
import ru.gigastack.model.DataType;
import ru.gigastack.model.StatsRezult;
import ru.gigastack.stats.impl.FloatStatsCollector;
import ru.gigastack.stats.impl.IntStatsCollector;
import ru.gigastack.stats.impl.StringStatsCollector;

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
}
