package ru.gigastack.lineProcessing.impl;

import ru.gigastack.lineProcessing.TypeParser;

import java.math.BigInteger;
import java.util.Optional;

public class IntegerParser implements TypeParser<BigInteger> {
    @Override
    public Optional<BigInteger> tryParse(String line) {
        try {
            BigInteger bi = new BigInteger(line);
            return Optional.of(bi);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
