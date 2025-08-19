package ru.gigastack.io;

import lombok.RequiredArgsConstructor;
import ru.gigastack.LineProcessing.LineClassifier;
import ru.gigastack.enums.DataType;
import ru.gigastack.exception.BusinessException;
import ru.gigastack.exception.BusinessExceptionErrorCode;

import java.io.IOException;

@RequiredArgsConstructor
public class LineProcessor {
    private final LineClassifier lineClassifier;
    private final TypeWriters writers;

    public void lineProcess(String line, int lineNumber) throws BusinessException {
        if (line == null || line.isBlank()) return;

        DataType dataType = lineClassifier.classify(line);

        try {
            writers.write(dataType,line);
        }catch (IOException e){
            throw new BusinessException(BusinessExceptionErrorCode.FILE_READ_ERROR,
                    String.format("Ошибка записи под номером: %s; строка: %s", line,lineNumber),
                    e);
        }

        //Сюда вставить обработку статистики сразу при записи
    }
}
