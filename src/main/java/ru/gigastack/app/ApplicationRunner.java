package ru.gigastack.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.gigastack.io.FileLineReader;
import ru.gigastack.io.TypedLineWriter;
import ru.gigastack.lineProcessing.LineClassifier;
import ru.gigastack.lineProcessing.LineProcessor;
import ru.gigastack.lineProcessing.impl.FloatParser;
import ru.gigastack.lineProcessing.impl.IntegerParser;
import ru.gigastack.lineProcessing.impl.LineClassifierImpl;
import ru.gigastack.model.Params;
import ru.gigastack.exception.BusinessException;
import ru.gigastack.exception.BusinessExceptionErrorCode;
import ru.gigastack.stats.console.StatsPrinter;
import ru.gigastack.stats.StatisticsService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ApplicationRunner {
    static final Logger logger = LogManager.getLogger(ApplicationRunner.class);

    public static void run(Params params) throws BusinessException {
        Path outputDir = Path.of(params.outputDir() == null ? "." : params.outputDir());

        logger.info("Старт обработки; Выходная папка='{}', префикс='{}', append={}, файлов={}",
                outputDir, params.prefix(), params.append(), params.inputFiles().size());

        StatisticsService statistics = new StatisticsService(params.statistic());

        try(TypedLineWriter writers = new TypedLineWriter(outputDir, params.prefix(), params.append())) {
            LineClassifier lineClassifier = new LineClassifierImpl(new IntegerParser(),new FloatParser());

            LineProcessor lineProcessor = new LineProcessor(lineClassifier,writers, statistics);

            FileLineReader reader = new FileLineReader(lineProcessor);

            for (Path in : params.inputFiles()){
                if(!Files.isRegularFile(in)){
                    logger.warn("Пропуск: {} — не существует или не файл", in.toAbsolutePath());
                }else {
                    try {
                        logger.info("Начата обработка файла: {}",in);
                        reader.startFileLinesProcess(in);
                        logger.info("Завершена обработка файла: {}",in);
                    } catch (BusinessException e){
                        logger.error("Файл пропущен из-за ошибки: {}", in, e);
                    }
                }
            }
        } catch (IOException e) {
            throw new BusinessException(BusinessExceptionErrorCode.FILE_ERROR,
                    "Ошибка при работе с файлами",e);
        }

        StatsPrinter.print(statistics.resultMap(), statistics.getMode());
    }
}
