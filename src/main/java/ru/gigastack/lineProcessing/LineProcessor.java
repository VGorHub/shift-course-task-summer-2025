package ru.gigastack.lineProcessing;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.gigastack.enums.DataType;
import ru.gigastack.exception.BusinessException;
import ru.gigastack.exception.BusinessExceptionErrorCode;
import ru.gigastack.io.TypeWriters;
import ru.gigastack.stats.impl.StatisticsService;

import java.io.IOException;

@RequiredArgsConstructor
public class LineProcessor {
    private final static Logger logger = LogManager.getLogger(LineProcessor.class);
    private final LineClassifier lineClassifier;
    private final TypeWriters writers;
    private final StatisticsService statistics;


    public void lineProcess(String line, int lineNumber) throws BusinessException {
        if (line == null || line.isBlank()) return;

        DataType dataType = lineClassifier.classify(line);

        try {
            writers.write(dataType,line);
            statistics.onLine(dataType, line);
        }catch (IOException e){
            logger.error("Ошибка записи строки #{}: {}", lineNumber, line, e);
            throw new BusinessException(BusinessExceptionErrorCode.FILE_WRITE_ERROR,
                    String.format("Ошибка записи под номером: %s; строка: %s",lineNumber, line), e);
        }
    }
}
