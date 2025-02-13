import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

  public static List<BufferedReader> getOpenFiles(String[] filenames) {
    List<BufferedReader> openedFiles = new ArrayList<>();

    for (String filename : filenames) {
      try {
        BufferedReader openedFile = new BufferedReader(
            new FileReader(filename));
        openedFiles.add(openedFile);

      } catch (FileNotFoundException e) {
        System.out.println("Ошибка: файл '" + filename + "' не найден.");
      }
    }
    return openedFiles;
  }

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Ошибка: укажите хотя бы один файл.");
      return;
    }
    List<BufferedReader> openedFiles = getOpenFiles(args);
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
    // Закрываем все файлы
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




