package org.utility;

public class FileStat {

  private static int intCount = 0;
  private static int floatCount = 0;
  private static int stringCount = 0;

  private static long intSum = 0;
  private static long intMin = Long.MAX_VALUE; // Изначально максимально возможное значение
  private static long intMax = Long.MIN_VALUE; // Изначально минимально возможное значение
  private static double intAvg = 0;

  private static double floatSum = 0.0;
  private static double floatMin = Double.MAX_VALUE; // Изначально максимально возможное значение
  private static double floatMax = -Double.MAX_VALUE; // Изначально минимально возможное значение
  private static double floatAvg = 0;

  private static int minStringLength = Integer.MAX_VALUE; // Изначально минимальная длина строки
  private static int maxStringLength = Integer.MIN_VALUE; // Изначально максимальная длина строки

  // Приватный конструктор, чтобы предотвратить создание экземпляров
  private FileStat() {
    throw new UnsupportedOperationException(
        "Этот класс утилитарный и не предназначен для создания экземпляров.");
  }

  public static void processInt(long value) {
    intCount++;
    intSum += value;
    intMin = Math.min(intMin, value);
    intMax = Math.max(intMax, value);
    intAvg = (double) intSum / intCount;
  }

  public static void processFloat(double value) {
    floatCount++;
    floatSum += value;
    floatMin = Math.min(floatMin, value);
    floatMax = Math.max(floatMax, value);
    floatAvg = floatSum / floatCount;
  }

  public static void processString(String value) {
    stringCount++;
    int length = value.length();
    minStringLength = Math.min(minStringLength, length);
    maxStringLength = Math.max(maxStringLength, length);
  }

  public static String getIntStat() {
    if (intCount == 0) {
      return "Integers: No data";
    }
    return String.format(
        "Integers%nSum: %d%nMin: %d%nMax: %d%nAvg: %s",
        intSum, intMin, intMax, intAvg
    );
  }

  public static String getFloatStat() {
    if (floatCount == 0) {
      return "Floats: No data";
    }
    return "Floats\n" +
        "Sum: " + floatSum + "\n" +
        "Min: " + floatMin + "\n" +
        "Max: " + floatMax + "\n" +
        "Avg: " + floatAvg;
  }

  public static String getShortStat() {
    return "Integers: " + intCount + "\nFloats: " + floatCount + "\nStrings: " + stringCount;
  }

  public static String getStringStat() {
    return String.format(
        "Strings%nMin length: %s%nMax length: %s",
        stringCount > 0 ? minStringLength : "N/A",
        stringCount > 0 ? maxStringLength : "N/A"
    );
  }

  public static String getFullStat() {
    return String.format("%s%n%s%n%s%n%s", getShortStat(), getIntStat(), getFloatStat(),
        getStringStat());
  }

  public static void printShortStat() {
    System.out.println(getShortStat());
  }

  public static void printFullStat() {
    System.out.println(getFullStat());
  }
}
