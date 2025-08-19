package ru.gigastack.LineProcessing.impl;

import lombok.RequiredArgsConstructor;
import ru.gigastack.LineProcessing.LineClassifier;
import ru.gigastack.LineProcessing.TypeParser;
import ru.gigastack.enums.DataType;

@RequiredArgsConstructor
public class LineClassifierImpl implements LineClassifier {
    private final TypeParser<Long> intParser;
    private final TypeParser<?> floatParser;

    @Override
    public DataType classify(String line) {
        if (intParser.tryParse(line).isPresent()){
            return DataType.INTEGER;
        } else if (floatParser.tryParse(line).isPresent()) {
            return DataType.FLOAT;
        }
        else {
            return DataType.STRING;
        }
    }
}
