package ru.gigastack.LineProcessing;

import java.util.Optional;

public interface TypeParser {
    Optional<Object> tryParse(String line);
}
