import java.io.BufferedReader;
import java.util.List;

public class App {

  public static boolean appendMode = false;


  // входная точка для запуска программы
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Ошибка: укажите хотя бы один файл.");
      return;
    }

    appendMode = checkAppendOption(args);
    try {
      FileWriterManager.initWriters();
      List<BufferedReader> openedFiles = FileReaderManager.getOpenedFiles(args);
      FileParser.parseFile(openedFiles);
      FileReaderManager.closeFiles(openedFiles);
    } finally {
      FileWriterManager.closeWriters();
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
}




