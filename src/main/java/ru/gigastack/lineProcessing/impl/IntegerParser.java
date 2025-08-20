package ru.gigastack.lineProcessing.impl;

import ru.gigastack.lineProcessing.TypeParser;

import java.util.Optional;

public class IntegerParser implements TypeParser {
    @Override
    public Optional<Long> tryParse(String line) {
        try {
            return Optional.of(Long.parseLong(line));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
