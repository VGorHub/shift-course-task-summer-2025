package ru.gigastack.lineProcessing;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.gigastack.model.DataType;
import ru.gigastack.exception.BusinessException;
import ru.gigastack.io.TypedLineWriter;
import ru.gigastack.stats.StatisticsService;

import java.io.IOException;

@RequiredArgsConstructor
public class LineProcessor {
    private final static Logger logger = LogManager.getLogger(LineProcessor.class);
    private final LineClassifier lineClassifier;
    private final TypedLineWriter writers;
    private final StatisticsService statistics;


    public void lineProcess(String line, int lineNumber) throws BusinessException {
        if (line == null || line.isBlank()) return;

        DataType dataType = lineClassifier.classify(line);

        try {
            writers.write(dataType,line);
            statistics.onLine(dataType, line);
        }catch (IOException e){
            logger.error("Ошибка записи строки #{}: {}", lineNumber, line, e);
        }
    }
}
