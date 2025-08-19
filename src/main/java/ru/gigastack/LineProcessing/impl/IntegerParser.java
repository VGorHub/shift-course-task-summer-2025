package ru.gigastack.LineProcessing.impl;

import ru.gigastack.LineProcessing.TypeParser;

import java.util.Optional;

public class IntegerParser implements TypeParser {
    @Override
    public Optional<Object> tryParse(String line) {
        try {
            return Optional.of(Integer.parseInt(line));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
