package org.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArgumentParser {

  private static final Logger logger = Logger.getLogger(ArgumentParser.class.getName());
  private static final String USER_DIR = System.getProperty("user.dir");
  private static final Path ROOT_PROJECT = Paths.get(USER_DIR);
  private static final List<String> fileNames = new ArrayList<>();
  private static Path outputDirectory = Paths.get(
      USER_DIR); // Изначально дефолтный путь (корень проекта)
  private static boolean appendMode = false;
  private static String prefix = "";
  private static boolean shortStatOption = false;
  private static boolean fullStatOption = false;

  private ArgumentParser() {
    throw new UnsupportedOperationException(
        "Этот класс утилитарный и не предназначен для создания экземпляров.");
  }

  private static void processOutputDirectory(String path) {
    Path outputPath;

    outputPath = (path == null || path.isEmpty()) ? ROOT_PROJECT : Paths.get(path);

    try {

      if (!Files.exists(outputPath)) {
        Files.createDirectories(outputPath);
      } else if (!Files.isDirectory(outputPath)) {

        if (logger.isLoggable(Level.WARNING)) {
          logger.log(Level.WARNING, String.format(
              "Указанный путь не является директорией: %s. Файлы будут сохранены в корне проекта.",
              outputPath));
        }
        outputPath = ROOT_PROJECT;
      }
    } catch (IOException e) {

      logger.log(Level.SEVERE,
          String.format("Ошибка при создании выходной директории: %s", outputPath), e);
      outputPath = ROOT_PROJECT;
    }

    outputDirectory = outputPath;
  }


  public static void parseArguments(String[] args) {
    int i = 0;
    while (i < args.length) {
      String arg = args[i]; // Текущий аргумент

      try {
        switch (arg) {
          case "-a":
            appendMode = true;
            i++; // Переходим к следующему аргументу
            break;
          case "-o":
            if (i + 1 < args.length) {
              outputDirectory = Paths.get(args[i + 1]);
              processOutputDirectory(outputDirectory.toString());
              i += 2;
            } else {
              processOutputDirectory(null); // Путь не указан — сохраняем в корень
              i++;
            }
            break;
          case "-p":
            if (i + 1 < args.length) {
              prefix = args[i + 1];
              i += 2;
            } else {
              logger.log(Level.WARNING, "Ошибка: после -p необходимо указать префикс.");
              i++;
            }
            break;
          case "-s":
            shortStatOption = true;
            i++;
            break;
          case "-f":
            fullStatOption = true;
            i++;
            break;
          default:
            fileNames.add(arg);
            i++;
            break;
        }
      } catch (Exception e) {
        logger.log(Level.WARNING, String.format("Ошибка при обработке аргумента: %s", arg), e);
        i++;
      }
    }

    // Проверка на наличие файлов для обработки
    if (fileNames.isEmpty()) {
      logger.log(Level.SEVERE, "Ошибка: укажите хотя бы один файл для обработки.");
      System.exit(1);
    }
  }

  public static boolean isAppendMode() {
    return appendMode;
  }

  public static String getOutputDirectory() {
    return outputDirectory.toString();
  }

  public static String getPrefix() {
    return prefix;
  }

  public static List<String> getFileNames() {
    return fileNames;
  }

  public static boolean isShortStatOption() {
    return shortStatOption;
  }

  public static boolean isFullStatOption() {
    return fullStatOption;
  }
}
