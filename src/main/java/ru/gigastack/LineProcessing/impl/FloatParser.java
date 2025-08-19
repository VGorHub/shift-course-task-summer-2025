package ru.gigastack.LineProcessing.impl;

import ru.gigastack.LineProcessing.TypeParser;

import java.util.Optional;

public class FloatParser implements TypeParser {
    @Override
    public Optional<Object> tryParse(String line) {
        try {
            return Optional.of(Float.parseFloat(line));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
