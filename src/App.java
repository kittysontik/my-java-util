import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

  // входная точка для запуска программы
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Ошибка: укажите хотя бы один файл.");
      return;
    }

    List<BufferedReader> openedFiles = getOpenFiles(args);
    printFiles(openedFiles);
    closeFiles(openedFiles);

  }

  // метод, к-ый получает открытые файлы
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

  // метод, к-ый закрвывает все файлы
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




