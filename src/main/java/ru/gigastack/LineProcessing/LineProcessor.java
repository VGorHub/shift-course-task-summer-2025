package ru.gigastack.LineProcessing;

import lombok.RequiredArgsConstructor;
import ru.gigastack.enums.DataType;
import ru.gigastack.exception.BusinessException;
import ru.gigastack.exception.BusinessExceptionErrorCode;
import ru.gigastack.io.TypeWriters;

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
            throw new BusinessException(BusinessExceptionErrorCode.FILE_WRITE_ERROR,
                    String.format("Ошибка записи под номером: %s; строка: %s", line,lineNumber),
                    e);
        }

        //Сюда вставить обработку статистики сразу при записи
    }
}
