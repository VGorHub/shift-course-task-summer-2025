# РидМишка SHIFT Course Task Summer 2025

### Java 21
### Gradle 8.10

## Зависимости
- `log4j-core:2.24.3` [Для логирования](https://logging.apache.org/log4j/2.x/components.html)
- `commons-cli:1.9.0` [Для работы с входными опциями](https://commons.apache.org/proper/commons-cli/)
- `lombok:1.18.38` [Чет мне лень стало писать Сетеры](https://projectlombok.org)
- JUnit5 для тестов


## Запускаю вот так
```bash
./gradlew run --args=" -o out -p test_ -s input1.txt input2.txt "
```


## Запуск через сборку дистрибутивов
```bash
./gradlew installDist
build/install/shiftCourseTaskSummer2025/bin/shiftCourseTaskSummer2025  -o out -p test_ -s input/input1.txt input/input2.txt 
```

>На Windows(cmd) :
> P.S: Не тестил, поэтому сори если не запуститься 🤗
> ```bash
> gradlew.bat installDist
> build\install\shiftCourseTaskSummer2025\bin\shiftCourseTaskSummer2025.bat -o out -p test_ -s input\input1.txt input\input2.txt
> ```


## Особенности реализации
- Файл читает построчно, не так что все "прочитал и потом обработал", чтоб не было переполнения памяти.
- Обработка идет сразу во время чтения, а именно:
  - Запись по файлам. Файлы создаются лениво
  - Сбор статистики после успешной записи в файл 
- Логи пишутся в консоль (не знаю насколько правильное решение оставлять чтоб их видел пользователь, но решил что для проверяющих это будет полезнее и информативнее)
- Так же логи пишутся в файл `target/app.log`
- Присутствует опций `-h` для вывода справки
- Вместо int использую BigInteger (чтоб `1234567890123456789` записывался в integers.txt и другие большие значения)
- Вместо float/double использую BigDecimal (чтоб не было Nan Infinity и прочих конструкций, и чтоб любые большие значения правильно парсились) 

## Нюансы
- Числа по типу `" 06"` считаются строками
- Пустые строки `"""` пропускаются и не записываются в `string.txt`
- Флаг `-f` приоритетнее `-s`, при наличии двух флагов побеждает `-f`
