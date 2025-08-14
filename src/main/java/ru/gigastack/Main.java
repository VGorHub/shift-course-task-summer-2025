package ru.gigastack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.gigastack.services.AppFacade;
import ru.gigastack.services.impl.AppFacadeImpl;


public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            AppFacade app = new AppFacadeImpl();
            app.run(args);
        }catch (Exception e){
            logger.error(String.format("Произошла непредвиденная ошибка ошибка: {}",e));
            System.exit(500);
        }


    }
}