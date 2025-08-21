package ru.gigastack.stats.impl;

import ru.gigastack.stats.StatsCollector;
import ru.gigastack.model.StatsRezult;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class IntStatsCollector implements StatsCollector {
    private long count = 0;
    private Long min = null;
    private Long max = null;
    private BigInteger sum = BigInteger.valueOf(0);

    @Override
    public void accept(String line) {
        long i = Long.parseLong(line);
        count++;
        if (count == 1){
            min = i;
            max = i;
        }else {
            min = Math.min(min,i);
            max = Math.max(max,i);
        }
        sum = sum.add(BigInteger.valueOf(i));
    }

    @Override
    public StatsRezult rezult() {
        if (count == 0 ){
            return new StatsRezult(0,"");
        }

        String detail = String.format("min=%s, max=%s, sum=%s, avg=%s", min, max, sum, avgAsString());

        return new StatsRezult(count,detail);
    }

    private String avgAsString() {
        return new BigDecimal(sum)
                .divide(new BigDecimal(count),10, RoundingMode.HALF_UP)
                .stripTrailingZeros()
                .toPlainString();
    }
}
