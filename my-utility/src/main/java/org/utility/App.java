package org.utility;

import java.io.BufferedReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

  private static final Logger logger = Logger.getLogger(App.class.getName());

  // входная точка для запуска программы
  public static void main(String[] args) {
    try {
      ArgumentParser parser = new ArgumentParser();
      parser.parseArguments(args);

      boolean appendMode = parser.isAppendMode();
      String outputDirectory = parser.getOutputDirectory();
      String prefix = parser.getPrefix();  // Получаем префикс
      List<String> fileNames = parser.getFileNames();

      FileWriterManager.initWriters(prefix, outputDirectory, appendMode);
      List<BufferedReader> openedFiles = FileReaderManager.getOpenedFiles(fileNames);
      FileParser.parseFile(openedFiles);
      FileReaderManager.closeFiles(openedFiles);
      if (parser.isShortStatOption()) {
        System.out.println(FileStat.getShortStat());  // Печать статистики
      } else if (parser.isFullStatOption()) {
        System.out.println(FileStat.getFullStat());
      }

    } catch (IllegalArgumentException e) {
      logger.log(Level.SEVERE, e.getMessage());
      System.exit(1);
    } finally {
      FileWriterManager.closeWriters();
    }
  }

}



