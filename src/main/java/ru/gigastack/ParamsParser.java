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

    public static Params parse(String[] args) throws ParseException{
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        Options options = initiolizateOptions();

        try {
            cmd = parser.parse(options,args);
        } catch (ParseException e) {
            formatter.printHelp("java [options] file1 file2 ...", options);
            throw e;
        }

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

    private static Options initiolizateOptions(){
        Options options = new Options();

        Option outputDir = Option.builder()
                .option("o")
                .longOpt("output")
                .hasArg(true)
                .desc("Путь до результута")
                .build();
        options.addOption(outputDir);

        Option prefix = Option.builder()
                .option("p")
                .longOpt("prefix")
                .hasArg(true)
                .desc("Префикс выходных файлов")
                .build();
        options.addOption(prefix);

        Option append = Option.builder()
                .option("a")
                .longOpt("append")
                .hasArg(false)
                .desc("Режим добавления в конец файла")
                .build();
        options.addOption(append);

        Option shortStatistic = Option.builder()
                .option("s")
                .longOpt("short")
                .hasArg(false)
                .desc("Краткая Стистика")
                .build();
        options.addOption(shortStatistic);

        Option fullStatistic = Option.builder()
                .option("f")
                .longOpt("full")
                .hasArg(false)
                .desc("Полная Статистика")
                .build();
        options.addOption(fullStatistic);


        return options;
    }
}
