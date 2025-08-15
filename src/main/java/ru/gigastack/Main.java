package ru.gigastack;

import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            Params params = null;
            try {
                params = ParamParser.parseArgs(args);
            }catch (ParseException e){
                System.exit(2);
            }
            Busines.run(params);
        }catch (Exception e){
            logger.error("Произошла не предвиденная ошибка: {}", e.getMessage());
            System.exit(3);
        }
    }
}