package ru.gigastack;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

public class ParamParser {
    public static Params parseArgs(String[] args) throws ParseException {

        CommandLine cmd = CommandLineParser.parse(args);

        Params.Statistic statistic = Params.Statistic.SHORT;

        if (cmd.hasOption("f")){
            statistic = Params.Statistic.FULL;
        }

        Params params = new Params(
                cmd.getOptionValue("o"),
                cmd.getOptionValue("p"),
                cmd.hasOption("a"),
                statistic,
                cmd.getArgList()
        );
        return params;
    }
}
