package org.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileParser {

  private static final Logger logger = Logger.getLogger(FileParser.class.getName());

  // Приватный конструктор, чтобы предотвратить создание экземпляров
  private FileParser() {
    throw new UnsupportedOperationException(
        "Этот класс утилитарный и не предназначен для создания экземпляров.");
  }

  public static void parseFile(List<BufferedReader> openedFiles) {
    boolean filesHaveLines = true;
    while (filesHaveLines) {
      filesHaveLines = false;
      for (BufferedReader openedFile : openedFiles) {
        try {
          String line = openedFile.readLine();
          if (line != null) {
            processLine(line);
            filesHaveLines = true;
          }
        } catch (IOException e) {
          logger.log(Level.SEVERE, "Ошибка при чтении файлов.");
        }
      }
    }
  }

  public static void processLine(String line) {
    line = line.trim();

    Optional<Long> intValue = parseInteger(line);
    if (intValue.isPresent()) {
      FileStat.processInt(intValue.get()); // Получаем значение, так как оно точно есть
      FileWriterManager.writeToFile(FileWriterManager.intWriter, line);
      return;
    }

    Optional<Double> floatValue = parseFloat(line);
    if (floatValue.isPresent()) {
      FileStat.processFloat(floatValue.get());
      FileWriterManager.writeToFile(FileWriterManager.floatWriter, line);
      return;
    }
    // Если не число, значит строка
    FileStat.processString(line);
    FileWriterManager.writeToFile(FileWriterManager.stringWriter, line);
  }

  public static Optional<Long> parseInteger(String str) {
    try {
      return Optional.of(Long.parseLong(str)); // Если успешно, возвращаем Optional с числом
    } catch (NumberFormatException e) {
      return Optional.empty(); // Если ошибка, возвращаем пустой Optional
    }
  }

  public static Optional<Double> parseFloat(String str) {
    try {
      return Optional.of(Double.parseDouble(str));
    } catch (NumberFormatException e) {
      return Optional.empty();
    }
  }
}
