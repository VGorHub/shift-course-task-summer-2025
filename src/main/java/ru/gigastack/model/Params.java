package ru.gigastack.model;

import java.nio.file.Path;
import java.util.List;

public record Params(
        String outputDir,
        String prefix,
        boolean append,
        Statistic statistic,
        List<Path> inputFiles
) {
    public enum Statistic{
        SHORT,
        FULL
    };
}
