package ru.gigastack.cli;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;
import ru.gigastack.model.Params;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParamAndCommandLineParserTest {

    @Test
    void validOptionsShortStatistic() throws Exception {
        String[] args = {"-o", "out", "-p", "pref_", "-a", "-s", "file1.txt", "file2.txt"};

        Params params = ParamParser.parseArgs(args);

        assertEquals("out", params.outputDir());
        assertEquals("pref_", params.prefix());
        assertTrue(params.append());
        assertEquals(Params.Statistic.SHORT, params.statistic());
        assertEquals(List.of(Path.of("file1.txt"), Path.of("file2.txt")), params.inputFiles());
    }

    @Test
    void validOptionsFullStatistic() throws Exception {
        String[] args = {"-o", "out", "-p", "pref_", "-a", "-f", "f1.txt", "f2.txt"};

        Params params = ParamParser.parseArgs(args);

        assertEquals("out", params.outputDir());
        assertEquals("pref_", params.prefix());
        assertTrue(params.append());
        assertEquals(Params.Statistic.FULL, params.statistic());
        assertEquals(List.of(Path.of("f1.txt"), Path.of("f2.txt")), params.inputFiles());
    }

    @Test
    void validOptionsSAndF() throws Exception {
        String[] args = {"-s", "-f", "a.txt", "b.txt"};

        Params params = ParamParser.parseArgs(args);

        assertEquals(Params.Statistic.FULL, params.statistic());
        assertEquals(2, params.inputFiles().size());
    }

    @Test
    void invalidZeroFilesThrowsParseException() {
        String[] args = { "-o", "out" };

        assertThrows(ParseException.class, () -> CommandLineParser.parse(args));
    }

}
