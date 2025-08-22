package ru.gigastack.lineProcessing.impl;

import ru.gigastack.lineProcessing.TypeParser;

import java.math.BigDecimal;
import java.util.Optional;

public class FloatParser implements TypeParser<BigDecimal> {
    @Override
    public Optional<BigDecimal> tryParse(String line) {
        try {
            BigDecimal bd = new BigDecimal(line);
            return Optional.of(bd);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
