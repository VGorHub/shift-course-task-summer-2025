package ru.gigastack;

import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.gigastack.cli.ParamParser;
import ru.gigastack.cli.Params;


public class Main {
    static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            Params params = null;
            try {
                params = ParamParser.parseArgs(args);
            }catch (ParseException e){
                System.exit(0);
            }
            ApplicationRunner.run(params);
        }catch (Exception e){
            logger.error("Непредвиденная ошибка приложения", e);
            System.exit(1);
        }
    }
}