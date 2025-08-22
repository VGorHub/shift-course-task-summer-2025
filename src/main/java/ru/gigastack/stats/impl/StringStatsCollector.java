package ru.gigastack.stats.impl;

import ru.gigastack.stats.StatsCollector;
import ru.gigastack.model.StatsResult;

public class StringStatsCollector implements StatsCollector {
    private long count = 0;
    private Integer minLen =null;
    private Integer maxLen = null;

    @Override
    public void accept(String line) {
        int len = line.length();
        count++;
        if (count == 1){
            minLen = len;
            maxLen = len;
        }else {
            minLen = Math.min(minLen,len);
            maxLen = Math.max(maxLen,len);
        }
    }

    @Override
    public StatsResult result() {
        if (count == 0 ){
            return new StatsResult(0,"");
        }

        String detail = "minLen=%s, maxLen=%s".formatted(minLen, maxLen);

        return new StatsResult(count, detail);
    }
}
