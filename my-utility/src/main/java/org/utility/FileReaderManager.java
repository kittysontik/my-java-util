package main.java.org.utility;


import java.io.*;
import java.util.List;
import java.util.ArrayList;


public class FileReaderManager {

  // метод, к-ый получает открытые файлы
  public static List<BufferedReader> getOpenedFiles(List<String> filenames) {
    List<BufferedReader> openedFiles = new ArrayList<>();

    for (String filename : filenames) {
      try {
        BufferedReader openedFile = new BufferedReader(new FileReader(filename));
        openedFiles.add(openedFile);

      } catch (FileNotFoundException e) {
        System.out.println("Ошибка: файл '" + filename + "' не найден.");
      }
    }
    return openedFiles;
  }

  // метод, к-ый закрывает все файлы
  public static void closeFiles(List<BufferedReader> openedFiles) {
    for (BufferedReader openedFile : openedFiles) {
      try {
        if (openedFile != null) {
          openedFile.close();
        }
      } catch (IOException e) {
        System.out.println("Ошибка при закрытии файла.");
      }
    }
  }
}
