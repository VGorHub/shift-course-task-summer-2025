package ru.gigastack.LineProcessing;

import ru.gigastack.enums.DataType;

public interface LineClassifier {
    DataType classify(String line);
}
