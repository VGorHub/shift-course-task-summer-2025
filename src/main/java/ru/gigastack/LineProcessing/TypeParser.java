package ru.gigastack.LineProcessing;

import java.util.Optional;

public interface TypeParser<T> {
    Optional<T> tryParse(String line);
}
