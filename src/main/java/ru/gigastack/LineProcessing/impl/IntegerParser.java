package ru.gigastack.LineProcessing.impl;

import ru.gigastack.LineProcessing.TypeParser;

import java.util.Optional;

public class IntegerParser implements TypeParser {
    @Override
    public Optional<Object> tryParse(String line) {

        return Optional.empty();
    }
}
