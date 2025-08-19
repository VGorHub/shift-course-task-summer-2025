package ru.gigastack.io;

import lombok.RequiredArgsConstructor;
import ru.gigastack.enums.DataType;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumMap;
import java.util.Map;

@RequiredArgsConstructor
public final class TypeWriters implements AutoCloseable{
    private final Path outDir;
    private final String prefix;
    private final boolean append;
    private final Map<DataType, BufferedWriter> writers = new EnumMap<>(DataType.class);

    public void write(DataType dataType,String line) throws IOException {
        BufferedWriter w = writers.get(dataType);
        if(w == null){
            Files.createDirectories(outDir);
            Path file = outDir.resolve(prefix + fileName(dataType));

            OpenOption[] opts = fileOpenOptions(append);

            w = Files.newBufferedWriter(file, StandardCharsets.UTF_8,opts);
            writers.put(dataType,w);
        }

        w.write(line);
        w.newLine();
    }
    private OpenOption[] fileOpenOptions(boolean append){
        if(append){
            return new OpenOption[]{
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            };
        }else {
            return new OpenOption[]{
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            };
        }
    }

    private String fileName(DataType dataType) {
        return switch (dataType){
            case INTEGER -> "integers.txt";
            case FLOAT -> "float.txt";
            case STRING -> "string.txt";
        };
    }

    @Override
    public void close() throws IOException {
        IOException first = null;
        for (BufferedWriter w : writers.values()) {
            try {
                if (w != null) w.close();
            } catch (IOException e) {
                if (first == null) first = e;
            }
        }
        if (first != null) throw first;
    }
}
