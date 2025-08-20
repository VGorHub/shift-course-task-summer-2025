package ru.gigastack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.gigastack.lineProcessing.LineClassifier;
import ru.gigastack.lineProcessing.LineProcessor;
import ru.gigastack.lineProcessing.impl.FloatParser;
import ru.gigastack.lineProcessing.impl.IntegerParser;
import ru.gigastack.lineProcessing.impl.LineClassifierImpl;
import ru.gigastack.cli.Params;
import ru.gigastack.exception.BusinessException;
import ru.gigastack.exception.BusinessExceptionErrorCode;
import ru.gigastack.io.FileLineProcessingService;
import ru.gigastack.io.TypeWriters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ApplicationRunner {
    static final Logger logger = LogManager.getLogger(ApplicationRunner.class);

    public static void run(Params params) throws IOException {
        Path outputDir = Path.of(params.outputDir() == null ? "." : params.outputDir());

        logger.info("Старт обработки; Выходная папка='{}', префикс='{}', append={}, файлов={}",
                outputDir, params.prefix(), params.append(), params.inputFiles().size());

        try(TypeWriters writers = new TypeWriters(outputDir, params.prefix(), params.append())){
            LineClassifier lineClassifier = new LineClassifierImpl(new IntegerParser(),new FloatParser());

            LineProcessor lineProcessor = new LineProcessor(lineClassifier,writers);

            FileLineProcessingService service = new FileLineProcessingService(lineProcessor);


            for (Path in : params.inputFiles()){
                if(!Files.isRegularFile(in)){
                    logger.warn("Не корректеный путь:{}  : не является файлом ли не луществут; Путь пропускается", in.toAbsolutePath());
                }else {
                    logger.info("Начата обработка файла: {}",in);
                    service.startFileLinesProcess(in);
                    logger.info("Завершена обработка файла: {}",in);
                }

            }
        } catch (IOException e) {
            throw new BusinessException(BusinessExceptionErrorCode.FILE_WRITE_ERROR,
                    "Ошибка записи в файл",e);
        }
    }
}
