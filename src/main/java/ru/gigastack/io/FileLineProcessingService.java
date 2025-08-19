package ru.gigastack.io;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.gigastack.exception.BusinessException;
import ru.gigastack.exception.BusinessExceptionErrorCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@RequiredArgsConstructor
public class FileLineProcessingService {
    private final static Logger logger = LogManager.getLogger(FileLineProcessingService.class);
    private final LineProcessor lineProcessor;

    public void startFileLinesProcess(Path path) throws BusinessException {
        int lineNumber = 1;

        try(InputStream is = Files.newInputStream(path);
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr)
        ) {
            String line;
            while ((line = br.readLine()) != null){
                if (!line.trim().isEmpty()){
                    lineProcessor.lineProcess(line,lineNumber);
                }
                lineNumber++;
            }
        } catch (IOException e) {
            throw new BusinessException(BusinessExceptionErrorCode.FILE_READ_ERROR,
                    String.format("Ошибка при чтении файла: %s",path),
                    e);
        } catch (BusinessException e){
            throw e;
        }
    }
}
