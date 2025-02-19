package org.utility;

import java.io.BufferedReader;
import java.util.List;

public class App {

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
      }

    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      System.exit(1);
    } finally {
      FileWriterManager.closeWriters();
    }
  }

}



