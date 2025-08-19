package ru.gigastack.io;

import lombok.RequiredArgsConstructor;
import ru.gigastack.LineProcessing.LineClassifier;
import ru.gigastack.enums.DataType;
import ru.gigastack.exception.BusinessException;

@RequiredArgsConstructor
public class LineProcessor {
    private final LineClassifier lineClassifier;

    public void lineProcess(String line, int lineNumber) throws BusinessException {
        DataType dataType = lineClassifier.classify(line);

        if (dataType.equals(DataType.INTEGER)){

        } else if (dataType.equals(DataType.FLOAT)) {

        }else {

        }
    }
}
