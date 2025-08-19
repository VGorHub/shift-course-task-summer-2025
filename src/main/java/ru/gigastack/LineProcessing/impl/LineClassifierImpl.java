package ru.gigastack.LineProcessing.impl;

import ru.gigastack.LineProcessing.LineClassifier;
import ru.gigastack.LineProcessing.TypeParser;
import ru.gigastack.enums.DataType;

public class LineClassifierImpl implements LineClassifier {
    @Override
    public DataType classify(String line) {
        TypeParser intParser = new IntegerParser();
        TypeParser floatParser = new FloatParser();
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
