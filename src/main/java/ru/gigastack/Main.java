package ru.gigastack;

import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.gigastack.app.ApplicationRunner;
import ru.gigastack.cli.HelpRequested;
import ru.gigastack.cli.ParamParser;
import ru.gigastack.model.Params;


public class Main {
    static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            Params params = null;
            try {
                params = ParamParser.parseArgs(args);
            } catch (HelpRequested hr){
                System.exit(0);
            } catch (ParseException e){
                logger.error("Произошла ошибка парсинга аргументов: {}", e.getMessage());
                System.exit(2);
            }
            ApplicationRunner.run(params);
        }catch (Exception e){
            logger.error("Непредвиденная ошибка приложения", e);
            System.exit(1);
        }
    }
}