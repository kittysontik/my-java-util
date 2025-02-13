import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Ошибка: укажите хотя бы один файл.");
      return;
    }
    // Создаем список для всех файлов
    List<BufferedReader> readers = new ArrayList<>();

    try {
      for (String filename : args) {
        try {
          FileReader fileReader = new FileReader(filename); // Открываем файл
          BufferedReader bufferedReader = new BufferedReader(
              fileReader); // Оборачиваем для построчного чтения
          readers.add(bufferedReader); // Добавляем в список

        } catch (FileNotFoundException e) {
          System.out.println("Ошибка: файл '" + filename + "' не найден.");
        }
      }

      boolean filesHaveLines = true;
      while (filesHaveLines) {
        filesHaveLines = false;
        for (BufferedReader reader : readers) {
          String line = reader.readLine();
          if (line != null) {
            System.out.println(line);
            filesHaveLines = true;
          }
        }
      }
    } catch (IOException e) {
      System.out.println("Ошибка при чтении файлов.");
    } finally {
      // Закрываем все файлы
      for (BufferedReader reader : readers) {
        try {
          if (reader != null) {
            reader.close();
          }
        } catch (IOException e) {
          System.out.println("Ошибка при закрытии файла.");
        }
      }
    }
  }
}




