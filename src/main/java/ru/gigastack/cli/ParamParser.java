package ru.gigastack.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ParamParser {
    public static Params parseArgs(String[] args) throws ParseException {

        CommandLine cmd = CommandLineParser.parse(args);

        Params.Statistic statistic = Params.Statistic.SHORT;

        if (cmd.hasOption("f")){
            statistic = Params.Statistic.FULL;
        }

        List<Path> paths = cmd.getArgList().stream()
                .map(Paths::get)
                .toList();

        Params params = new Params(
                cmd.getOptionValue("o"),
                cmd.getOptionValue("p"),
                cmd.hasOption("a"),
                statistic,
                paths
        );
        return params;
    }
}
