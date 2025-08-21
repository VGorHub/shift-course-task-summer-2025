package ru.gigastack.stats.impl;

import ru.gigastack.stats.StatsCollector;
import ru.gigastack.stats.StatsRezult;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class FloatStatsCollector implements StatsCollector {
    private long count = 0;
    private BigDecimal min = null;
    private BigDecimal max = null;
    private BigDecimal sum = BigDecimal.valueOf(0);


    @Override
    public void accept(String line) {
        BigDecimal f = new BigDecimal(line);
        count++;
        if(count == 1){
            min = f;
            max = f;
        }else {
            if (f.compareTo(min) < 0){
                min = f;
            }
            if (f.compareTo(max) > 0){
                max = f;
            }
        }
        sum = sum.add(f);
    }

    @Override
    public StatsRezult rezult() {
        if (count == 0 ){
            return new StatsRezult(0,"");
        }

        BigDecimal avg = sum.divide(BigDecimal.valueOf(count), MathContext.DECIMAL64).stripTrailingZeros();
        String detail = String.format("min=%s, max=%s, sum=%s, avg=%s", min, max, sum, avg);

        return new StatsRezult(count,detail);
    }

    @Override
    public boolean hasData() {
        return count > 0;
    }
}
