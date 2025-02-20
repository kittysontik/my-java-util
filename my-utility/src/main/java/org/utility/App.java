package org.utility;

import java.io.BufferedReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

  private static final Logger logger = Logger.getLogger(App.class.getName());

  public static void main(String[] args) {
    try {
      ArgumentParser.parseArguments(args);

      boolean appendMode = ArgumentParser.isAppendMode();
      String outputDirectory = ArgumentParser.getOutputDirectory();
      String prefix = ArgumentParser.getPrefix();
      List<String> fileNames = ArgumentParser.getFileNames();

      FileWriterManager.initWriters(prefix, outputDirectory, appendMode);
      List<BufferedReader> openedFiles = FileReaderManager.getOpenedFiles(fileNames);
      FileParser.parseFile(openedFiles);
      FileReaderManager.closeFiles(openedFiles);

      if (ArgumentParser.isShortStatOption()) {
        FileStat.printShortStat();
      } else if (ArgumentParser.isFullStatOption()) {
        FileStat.printFullStat();
      }

    } catch (IllegalArgumentException e) {
      logger.log(Level.SEVERE, e.getMessage());
      System.exit(1);
    } catch (Exception e) {
      if (logger.isLoggable(Level.SEVERE)) {
        logger.log(Level.SEVERE, String.format("Произошла неожиданная ошибка: %s", e.getMessage()),
            e);
      }
      System.exit(1);  // Завершаем программу, если произошла непредсказуемая ошибка
    } finally {
      FileWriterManager.closeWriters();
    }
  }
}
