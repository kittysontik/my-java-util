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
    fileParser(openedFiles);
    closeFiles(openedFiles);

  }

  // метод, к-ый получает открытые файлы
  public static List<BufferedReader> getOpenFiles(String[] filenames) {
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

  public static void fileParser(List<BufferedReader> openedFiles) {
    try {
      boolean filesHaveLines = true;
      while (filesHaveLines) {
        filesHaveLines = false;
        for (BufferedReader openedFile : openedFiles) {
          String line = openedFile.readLine();
          if (line != null) {
            if (isInteger(line)) {
              System.out.println("Целое число: " + line);
            } else if (isFloat(line)) {
              System.out.println("Дробное число: " + line);
            } else {
              System.out.println("Строка: " + line);
            }
            filesHaveLines = true;
          }
        }
      }
    } catch (IOException e) {
      System.out.println("Ошибка при чтении файлов.");
    }
  }


}




