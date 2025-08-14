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

    public static Params parse(String[] args){
        Options options = new Options();

        Option outputDir = Option.builder()
                .option("o")
                .longOpt("output")
                .hasArg(true)
                .desc("Путь до результута")
                .build();
        options.addOption(outputDir);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options,args);
        } catch (ParseException e) {
            logger.error(String.format("Ошибка Парсинга Аргументов: {}",e));
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }

        Params params = new Params();
        params.setOutputDir(cmd.getOptionValue("o"));
        return null;
    }
}
