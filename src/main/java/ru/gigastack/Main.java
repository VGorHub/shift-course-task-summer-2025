package ru.gigastack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.gigastack.servies.AppFacade;


public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            AppFacade.run(args);
        }catch (Exception e){
            logger.error(String.format("Произошла не предвидинная ошибка: {}",e));
            System.exit(500);
        }


    }
}