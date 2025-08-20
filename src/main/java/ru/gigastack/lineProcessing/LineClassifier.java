package ru.gigastack.lineProcessing;

import ru.gigastack.enums.DataType;

public interface LineClassifier {
    DataType classify(String line);
}
