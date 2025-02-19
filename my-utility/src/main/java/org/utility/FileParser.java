package org.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class FileParser {

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
          System.out.println("Ошибка при чтении файлов.");
        }
      }
    }
  }

  public static void processLine(String line) {
    if (isInteger(line)) {
      FileWriterManager.writeToFile(FileWriterManager.intWriter, line);
    } else if (isFloat(line)) {
      FileWriterManager.writeToFile(FileWriterManager.floatWriter, line);
    } else {
      FileWriterManager.writeToFile(FileWriterManager.stringWriter, line);
    }
  }

  public static boolean isInteger(String str) {
    try {
      Long.parseLong(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static boolean isFloat(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

}


