package ru.gigastack.lineProcessing.impl;

import lombok.RequiredArgsConstructor;
import ru.gigastack.lineProcessing.LineClassifier;
import ru.gigastack.lineProcessing.TypeParser;
import ru.gigastack.model.DataType;

import java.math.BigDecimal;
import java.math.BigInteger;

@RequiredArgsConstructor
public class LineClassifierImpl implements LineClassifier {
    private final TypeParser<BigInteger> intParser;
    private final TypeParser<BigDecimal> floatParser;

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
