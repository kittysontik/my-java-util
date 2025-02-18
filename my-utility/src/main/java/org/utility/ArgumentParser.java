package main.java.org.utility;

import java.util.ArrayList;
import java.util.List;

public class ArgumentParser {

  private boolean appendMode = false;
  private String outputDirectory = System.getProperty("user.dir"); // Текущая директория
  private List<String> fileNames = new ArrayList<>();
  private String prefix = "";

  // Геттер для префикса
  public String getPrefix() {
    return prefix;
  }

  public void parseArguments(String[] args) {
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-a":
          appendMode = true;
          break;
        case "-o":
          if (i + 1 < args.length) {
            outputDirectory = args[++i];
          } else {
            throw new IllegalArgumentException("Ошибка: после -o необходимо указать путь.");
          }
          break;
        case "-p":
          if (i + 1 < args.length) {
            prefix = args[++i];  // Сохраняем переданный префикс
          } else {
            throw new IllegalArgumentException("Ошибка: после -p необходимо указать префикс.");
          }
          break;
        default:
          fileNames.add(args[i]);
          break;
      }
    }

    if (fileNames.isEmpty()) {
      throw new IllegalArgumentException("Ошибка: укажите хотя бы один файл для обработки.");
    }
  }

  public boolean isAppendMode() {
    return appendMode;
  }

  public String getOutputDirectory() {
    return outputDirectory;
  }

  public List<String> getFileNames() {
    return fileNames;
  }

}
