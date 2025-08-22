package ru.gigastack.lineProcessing.impl;

import org.junit.jupiter.api.Test;
import ru.gigastack.model.DataType;
import ru.gigastack.lineProcessing.TypeParser;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineClassifierImplTest {
    private final TypeParser<BigInteger> intParser = new IntegerParser();
    private final TypeParser<BigDecimal> floatParser = new FloatParser();
    private final LineClassifierImpl classifier = new LineClassifierImpl(intParser, floatParser);

    @Test
    void integerHasPriorityOverFloat() {
        assertEquals(DataType.INTEGER, classifier.classify("42"));
    }

    @Test
    void scientificNotationIsFloat() {
        assertEquals(DataType.FLOAT, classifier.classify("1e3"));
    }

    @Test
    void overflowLongBecomesFloat() {
        assertEquals(DataType.FLOAT, classifier.classify("9223372036854775808"));
    }

    @Test
    void nonNumberIsString() {
        assertEquals(DataType.STRING, classifier.classify("hello"));
    }
}
