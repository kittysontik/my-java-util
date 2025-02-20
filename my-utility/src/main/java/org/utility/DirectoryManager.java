package org.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DirectoryManager {

  private static final Logger logger = Logger.getLogger(DirectoryManager.class.getName());
  private static final Path ROOT_PROJECT = Paths.get(System.getProperty("user.dir"));

  private static Path outputDirectory = ROOT_PROJECT;

  private DirectoryManager() {
    throw new UnsupportedOperationException(
        "Этот класс утилитарный и не предназначен для создания экземпляров.");
  }

  private static boolean isDirectory(Path path) {
    if ((!Files.isDirectory(path)) && (logger.isLoggable(Level.WARNING))) {
      logger.log(Level.WARNING, String.format(
          "Указанный путь не является директорией: %s. Файлы будут сохранены в корне проекта.",
          path));
      return false;
    }
    return true;
  }

  private static boolean isWritable(Path path) {
    if ((!Files.isWritable(path)) && (logger.isLoggable(Level.WARNING))) {
      logger.log(Level.WARNING, String.format(
          "Нет прав на запись в указанную директорию: %s. Файлы будут сохранены в корне проекта.",
          path));
      return false;
    }
    return true;
  }

  private static boolean createDirectoryIfNotExists() {
    try {
      if (!Files.exists(outputDirectory)) {
        Files.createDirectories(outputDirectory);
        return true;
      }
      return isDirectory(outputDirectory);
    } catch (IOException e) {
      logger.log(Level.SEVERE, String.format(
          "Ошибка при создании выходной директории: %s. Файлы будут сохранены в корне проекта.",
          outputDirectory), e);
      return false;
    }
  }

  private static boolean isValidDirectory() {
    if (!createDirectoryIfNotExists()) {
      return false;
    }

    return isDirectory(outputDirectory) && isWritable(outputDirectory);
  }

  private static void resolveOutputPath(String path) {
    if ((path == null || path.isEmpty())) {
      logger.log(Level.WARNING, "Путь для -o не указан. Файлы будут сохранены в корне проекта.");
      outputDirectory = ROOT_PROJECT;
      return;
    }

    Path newOutputPath = Paths.get(path);

    if ((!newOutputPath.isAbsolute()) && (logger.isLoggable(Level.WARNING))) {
      logger.log(Level.WARNING, String.format(
          "Указанный путь не является абсолютным: %s. Укажите полный путь, начиная со слэша (/).",
          newOutputPath));
      outputDirectory = ROOT_PROJECT;
      return;
    }
    outputDirectory = newOutputPath;
  }

  public static void processOutputDirectory(String path) {
    resolveOutputPath(path);

    if (!isValidDirectory()) {
      outputDirectory = ROOT_PROJECT;
    }
  }

  public static String getOutputDirectory() {
    return outputDirectory.toString();
  }
}
