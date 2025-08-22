package ru.gigastack.lineProcessing.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FloatParserTest {
    private final FloatParser parser = new FloatParser();

    @ParameterizedTest
    @DisplayName("Парсер распознает BigDecimal")
    @ValueSource(strings = {"0", "-0", "3.1415", "-0.001", "1.528535047E-25", "12.", ".5"})
    void parseDecimals(String input) {
        Optional<BigDecimal> res = parser.tryParse(input);
        assertTrue(res.isPresent(), "Ожидалось число: " + input);
    }

    @ParameterizedTest
    @DisplayName("Не парсит не валидные значения")
    @ValueSource(strings = {"", " ", "abc", "3,14", "+", "-"})
    void rejectGarbage(String input) {
        assertTrue(parser.tryParse(input).isEmpty());
    }
}
