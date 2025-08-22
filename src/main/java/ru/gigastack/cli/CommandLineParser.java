package ru.gigastack.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CommandLineParser {

    public static CommandLine parse(String[] args) throws ParseException{

        Options options = initializeOptions();

        try {
            CommandLine cmd = new DefaultParser().parse(options,args);
            if (cmd.hasOption("h")) {
                new HelpFormatter().printHelp("java [options] file1 file2 ...", options);
                throw new HelpRequested();
            }
            if (cmd.getArgList().size() < 1){
                throw new ParseException("Не достаточно параметров, требуется минимум 1 файл");
            }
            return cmd;
        } catch (ParseException e) {
            new HelpFormatter().printHelp("java [options] file1 file2 ...", options);
            throw e;
        }

    }

    private static Options initializeOptions(){
        Options options = new Options();

        Option help = Option.builder()
                .option("h")
                .longOpt("help")
                .hasArg(false)
                .desc("Показать справку")
                .build();
        options.addOption(help);

        Option outputDir = Option.builder()
                .option("o")
                .longOpt("output")
                .hasArg(true)
                .desc("Путь до результата")
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
                .desc("Краткая Статистика")
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
