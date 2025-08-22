package ru.gigastack.lineProcessing.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegerParserTest {
    private final IntegerParser parser = new IntegerParser();

    @ParameterizedTest
    @DisplayName("Парсер заспазнает валиднеы значения BigInteger")
    @ValueSource(strings = {"0", "1", "-1", "42", "" + Long.MAX_VALUE, "" + Long.MIN_VALUE})
    void shouldParseValidLongs(String input){
        Optional<BigInteger> res = parser.tryParse(input);
        assertTrue(res.isPresent(), "Успешное распознавание: " + input);
    }

    @ParameterizedTest
    @DisplayName("Не парсит не валидные значения BigInteger")
    @ValueSource(strings = {"1.0", "001.2", "1e3", "abc", "", " ", "\t", "12.", "+", "-"})
    void rejectNonIntegers(String input){
        Optional<BigInteger> res = parser.tryParse(input);
        assertTrue(res.isEmpty(), "Пустое Optional для: " + input);
    }
}
