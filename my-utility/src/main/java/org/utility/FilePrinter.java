package main.java.org.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class FilePrinter {

  // метод для поочередного построчного вывода содержимого файла в консоль
  public static void printFiles(List<BufferedReader> openedFiles) {
    try {
      boolean filesHaveLines = true;
      while (filesHaveLines) {
        filesHaveLines = false;
        for (BufferedReader openedFile : openedFiles) {
          String line = openedFile.readLine();
          if (line != null) {
            System.out.println(line);
            filesHaveLines = true;
          }
        }
      }
    } catch (IOException e) {
      System.out.println("Ошибка при чтении файлов.");
    }
  }


}
