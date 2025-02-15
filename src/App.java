import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

  private static boolean appendMode = false;
  private static BufferedWriter intWriter;
  private static BufferedWriter floatWriter;
  private static BufferedWriter stringWriter;


  // входная точка для запуска программы
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Ошибка: укажите хотя бы один файл.");
      return;
    }

    appendMode = checkAppendOption(args);
    try {
      initWriters();
      List<BufferedReader> openedFiles = getOpenedFiles(args);
      fileParser(openedFiles);
      closeFiles(openedFiles);
    } finally {
      closeWriters();
    }
  }

  private static boolean checkAppendOption(String[] args) {
    for (String arg : args) {
      switch (arg) {
        case "-a":
          return true;
      }
    }
    return false;
  }

  public static void initWriters() {
    try {
      intWriter = new BufferedWriter(new FileWriter("integers.txt", appendMode));
      floatWriter = new BufferedWriter(new FileWriter("floats.txt", appendMode));
      stringWriter = new BufferedWriter(new FileWriter("strings.txt", appendMode));
    } catch (IOException e) {
      System.out.println("Ошибка при создании файлов для записи.");
    }
  }

  public static void closeWriters() {
    try {
      if (intWriter != null) {
        intWriter.close();
      }
      if (floatWriter != null) {
        floatWriter.close();
      }
      if (stringWriter != null) {
        stringWriter.close();
      }
    } catch (IOException e) {
      System.out.println("Ошибка при закрытии файлов.");
    }
  }

  // метод, к-ый получает открытые файлы
  public static List<BufferedReader> getOpenedFiles(String[] filenames) {
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
    boolean filesHaveLines = true;
    while (filesHaveLines) {
      filesHaveLines = false;
      for (BufferedReader openedFile : openedFiles) {
        try {
          String line = openedFile.readLine();
          if (line != null) {
            if (isInteger(line)) {
              writeToFile(intWriter, line);
            } else if (isFloat(line)) {
              writeToFile(floatWriter, line);
            } else {
              writeToFile(stringWriter, line);
            }
            filesHaveLines = true;
          }
        } catch (IOException e) {
          System.out.println("Ошибка при чтении файлов.");
        }
      }
    }
  }

  public static void writeToFile(BufferedWriter writer, String line) {
    try {
      writer.write(line);
      writer.newLine();

    } catch (IOException e) {
      System.out.println("Ошибка при записи строки: " + line);
    }
  }

  public static void clearFiles(String filename) {
    try {
      new FileWriter(filename, false).close();
      new FileWriter(filename, false).close();
      new FileWriter(filename, false).close();
    } catch (IOException e) {
      System.out.println("Ошибка при очистке файлов.");
    }
  }
}




