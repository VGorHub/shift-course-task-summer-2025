package ru.gigastack.servies.Impl;

import ru.gigastack.Params;
import ru.gigastack.ParamsParser;
import ru.gigastack.servies.AppFacade;

public class AppFacadeImpl implements AppFacade {
    public void run(String[] arg){
        ParamsParser paramsParser ;

        Params params = paramsParser.parse(arg);
    }
}
