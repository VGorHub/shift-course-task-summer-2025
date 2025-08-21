package ru.gigastack.stats.console;

import ru.gigastack.model.Params;
import ru.gigastack.model.DataType;
import ru.gigastack.model.StatsRezult;

import java.util.Map;

public class StatsPrinter {
    public static void print(Map<DataType, StatsRezult> rez, Params.Statistic mode){
        StringBuilder sb = new StringBuilder("СТАТИСТИКА\n");
        for(DataType t : DataType.values()){
            StatsRezult statsRezult = rez.get(t);
            if(statsRezult != null && statsRezult.count() != 0){
                sb.append(t.name()).append(":count=").append(statsRezult.count());
                if (mode == Params.Statistic.FULL){
                    sb.append(", ").append(statsRezult.details());
                }
                sb.append("\n");
            }
        }
        String rezultString =  sb.toString();
        System.out.println(rezultString);
    }
}
