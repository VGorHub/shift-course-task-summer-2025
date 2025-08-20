package ru.gigastack.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.gigastack.enums.DataType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TypeWritersTest {
    @TempDir
    Path temp;

    @Test
    void createFileWithPrefixAndTruncate() throws IOException{
        Path outDir = temp.resolve("out");
        try (TypeWriters writers = new TypeWriters(outDir,"test_",false)){
            assertFalse(Files.exists(outDir));

            writers.write(DataType.STRING, "asb");

            Path strings = outDir.resolve("test_" + "string.txt");
            assertTrue(Files.exists(strings));

            assertFalse(Files.exists(outDir.resolve("test_" + "integers.txt")));
            assertFalse(Files.exists(outDir.resolve("test_" + "float.txt")));
        }


    }

    @Test
    void appendModeDoesNotTruncate() throws IOException {
        Path outDir = temp.resolve("out");
        Path strings = outDir.resolve("test_" + "string.txt");

        try (TypeWriters writers = new TypeWriters(outDir, "test_", false)) {
            writers.write(DataType.STRING, "first");
        }
        try (TypeWriters writers = new TypeWriters(outDir, "test_", true)) {
            writers.write(DataType.STRING, "second");
        }

        var content = Files.readAllLines(strings);
        assertEquals(2, content.size());
        assertEquals("first", content.get(0));
        assertEquals("second", content.get(1));
    }
}
