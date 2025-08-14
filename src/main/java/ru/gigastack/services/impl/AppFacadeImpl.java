package ru.gigastack.services.impl;

import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.gigastack.Params;
import ru.gigastack.ParamsParser;
import ru.gigastack.services.AppFacade;

public class AppFacadeImpl implements AppFacade {
    private static final Logger logger = LogManager.getLogger(AppFacadeImpl.class);

    public void run(String[] arg) {
        try {
            Params params = ParamsParser.parse(arg);
        }catch (ParseException e){
            logger.error("Произошла ошибка парсинга аргументов: {}", e.getMessage());
            System.exit(2);
        }


    }
}
