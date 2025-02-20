package org.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArgumentParser {

  private static final Logger logger = Logger.getLogger(ArgumentParser.class.getName());
  private static final List<String> fileNames = new ArrayList<>();

  private static boolean appendMode = false;
  private static String prefix = "";
  private static boolean shortStatOption = false;
  private static boolean fullStatOption = false;

  private ArgumentParser() {
    throw new UnsupportedOperationException(
        "Этот класс утилитарный и не предназначен для создания экземпляров.");
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
              DirectoryManager.processOutputDirectory(args[i + 1]);
              i += 2;
            } else {
              DirectoryManager.processOutputDirectory(null); // Путь не указан — сохраняем в корень
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
    return DirectoryManager.getOutputDirectory();
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
