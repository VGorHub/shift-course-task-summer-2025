package ru.gigastack.stats.console;

import ru.gigastack.model.Params;
import ru.gigastack.model.DataType;
import ru.gigastack.model.StatsResult;

import java.util.Map;

public class StatsPrinter {
    public static void print(Map<DataType, StatsResult> rez, Params.Statistic mode){
        StringBuilder sb = new StringBuilder("СТАТИСТИКА\n");
        for(DataType t : DataType.values()){
            StatsResult statsResult = rez.get(t);
            if(statsResult != null && statsResult.count() != 0){
                sb.append(t.name()).append(":count=").append(statsResult.count());
                if (mode == Params.Statistic.FULL){
                    sb.append(", ").append(statsResult.details());
                }
                sb.append("\n");
            }
        }
        String resultString =  sb.toString();
        System.out.println(resultString);
    }
}
