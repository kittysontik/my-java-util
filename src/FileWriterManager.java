import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterManager {

  static BufferedWriter intWriter;
  static BufferedWriter floatWriter;
  static BufferedWriter stringWriter;

  public static void initWriters() {
    try {
      intWriter = new BufferedWriter(new FileWriter("integers.txt", App.appendMode));
      floatWriter = new BufferedWriter(new FileWriter("floats.txt", App.appendMode));
      stringWriter = new BufferedWriter(new FileWriter("strings.txt", App.appendMode));
    } catch (IOException e) {
      System.out.println("Ошибка при создании файлов для записи.");
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


  public static void closeWriters() {
    try {
      if (FileWriterManager.intWriter != null) {
        FileWriterManager.intWriter.close();
      }
      if (FileWriterManager.floatWriter != null) {
        FileWriterManager.floatWriter.close();
      }
      if (FileWriterManager.stringWriter != null) {
        FileWriterManager.stringWriter.close();
      }
    } catch (IOException e) {
      System.out.println("Ошибка при закрытии файлов.");
    }
  }
}

