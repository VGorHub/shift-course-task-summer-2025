package ru.gigastack.lineProcessing;

import ru.gigastack.model.DataType;

public interface LineClassifier {
    DataType classify(String line);
}
