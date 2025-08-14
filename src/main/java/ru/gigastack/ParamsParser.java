package ru.gigastack;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParamsParser {
    private static Logger logger = LogManager.getLogger(ParamsParser.class);

    public static Params parse(String[] args) throws ParseException{
        Options options = initiolizateOptions();

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options,args);
        } catch (ParseException e) {
            formatter.printHelp("java [options] file1 file2 ...", options);
            throw e;
        }


        Params params = new Params("o");
        return params;
    }

    private static Options initiolizateOptions(){
        Options options = new Options();

        Option outputDir = Option.builder()
                .option("o")
                .longOpt("output")
                .hasArg(true)
                .desc("Путь до результута")
                .build();
        options.addOption(outputDir);

        return options;
    }
}
