package ru.gigastack.cli;

import java.util.List;

public record Params(
        String outputDir,
        String prefix,
        boolean append,
        Statistic statistic,
        List<String> inputFiles
) {
    public enum Statistic{
        SHORT,
        FULL
    };
}
