package ru.gigastack.stats.impl;

import ru.gigastack.stats.StatsCollector;
import ru.gigastack.model.StatsResult;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class IntStatsCollector implements StatsCollector {
    private long count = 0;
    private BigInteger min = null;
    private BigInteger max = null;
    private BigInteger sum = BigInteger.valueOf(0);

    @Override
    public void accept(String line) {
        BigInteger bi = new BigInteger(line);
        count++;
        if (count == 1){
            min = bi;
            max = bi;
        }else {
            if (bi.compareTo(min) < 0){
                min = bi;
            }
            if (bi.compareTo(max) > 0){
                max = bi;
            }
        }
        sum = sum.add(bi);
    }

    @Override
    public StatsResult result() {
        if (count == 0 ){
            return new StatsResult(0,"");
        }

        String detail = String.format("min=%s, max=%s, sum=%s, avg=%s", min, max, sum, avgAsString());

        return new StatsResult(count,detail);
    }

    private String avgAsString() {
        return new BigDecimal(sum)
                .divide(new BigDecimal(count),10, RoundingMode.HALF_UP)
                .stripTrailingZeros()
                .toPlainString();
    }
}
