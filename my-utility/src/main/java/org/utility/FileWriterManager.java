package main.java.org.utility;


import java.io.*;
import java.util.*;

public class FileWriterManager {

  static BufferedWriter intWriter;
  static BufferedWriter floatWriter;
  static BufferedWriter stringWriter;

  public static void initWriters(String prefix, String outputDirectory, boolean appendMode) {
    try {

      Map<String, String> fileNames = getFileNames(prefix, outputDirectory);

      intWriter = new BufferedWriter(new FileWriter(fileNames.get("integers"), appendMode));
      floatWriter = new BufferedWriter(new FileWriter(fileNames.get("floats"), appendMode));
      stringWriter = new BufferedWriter(
          new FileWriter(fileNames.get("strings"), appendMode));
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

  // метод для получения итогового названия выходного файла с префиксом и путем
  public static Map<String, String> getFileNames(String prefix, String outputDirectory) {
    Map<String, String> fileNames = new HashMap<>();
    fileNames.put("integers", getFileName(outputDirectory, prefix, "integers.txt"));
    fileNames.put("floats", getFileName(outputDirectory, prefix, "floats.txt"));
    fileNames.put("strings", getFileName(outputDirectory, prefix, "strings.txt"));
    return fileNames;
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

  public static String getFileName(String outputDirectory, String prefix, String fileName) {
    return outputDirectory + File.separator + prefix + fileName;
  }
}



