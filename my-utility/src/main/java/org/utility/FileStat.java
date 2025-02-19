package org.utility;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class FileStat {

  // Краткая статистика
  private static int intCount = 0;
  private static int floatCount = 0;
  private static int stringCount = 0;

  // Полная статистика для чисел
  private static BigInteger intSum = BigInteger.ZERO;
  private static long intMin = Long.MAX_VALUE;
  private static long intMax = Long.MIN_VALUE;
  private static BigDecimal floatSum = BigDecimal.ZERO;
  private static double floatMin = Double.MAX_VALUE;
  private static double floatMax = Double.MIN_VALUE;

  // Полная статистика для строк
  private static int minStringLength = Integer.MAX_VALUE;
  private static int maxStringLength = Integer.MIN_VALUE;

  // Приватный конструктор, чтобы предотвратить создание экземпляров
  private FileStat() {
    throw new UnsupportedOperationException(
        "Этот класс утилитарный и не предназначен для создания экземпляров.");
  }

  public static void addIntCount(long value) {
    intCount++;
    intSum = intSum.add(BigInteger.valueOf(value));
    intMin = Math.min(intMin, value);
    intMax = Math.max(intMax, value);
  }

  public static void addFloatCount(double value) {
    floatCount++;
    floatSum = floatSum.add(BigDecimal.valueOf(value));
    floatMin = Math.min(floatMin, value);
    floatMax = Math.max(floatMax, value);
  }

  public static void addStringCount(String value) {
    stringCount++;
    int length = value.length();
    minStringLength = Math.min(minStringLength, length);
    maxStringLength = Math.max(maxStringLength, length);
  }

  public static int getIntCount() {
    return intCount;
  }

  public static int getFloatCount() {
    return floatCount;
  }

  public static int getStringCount() {
    return stringCount;
  }

  // Метод для вычисления среднего значения для целых чисел
  public static String getIntAverage() {
    return intCount > 0 ? new BigDecimal(intSum)
        .divide(BigDecimal.valueOf(intCount), 2, RoundingMode.HALF_UP)
        .toString() : "N/A";
  }

  // Метод для вычисления среднего значения для чисел с плавающей точкой
  public static String getFloatAverage() {
    return floatCount > 0 ? floatSum
        .divide(BigDecimal.valueOf(floatCount), 2, RoundingMode.HALF_UP)
        .toString() : "N/A";
  }

  public static String getShortStat() {
    return "Integers: " + intCount + "\nFloats: " + floatCount + "\nStrings: " + stringCount;
  }

  public static String getIntStat() {
    return "Integers \nSum: " + intSum +
        "\nMin: " + (intCount > 0 ? intMin : "N/A") +
        "\nMax: " + (intCount > 0 ? intMax : "N/A") +
        "\nAvg: " + getIntAverage();
  }

  public static String getFloatStat() {
    return "Floats \nSum: " + floatSum +
        "\nMin: " + (floatCount > 0 ? floatMin : "N/A") +
        "\nMax: " + (floatCount > 0 ? floatMax : "N/A") +
        "\nAvg: " + getFloatAverage();
  }

  public static String getStringStat() {
    return "Strings \nMin length: " + (stringCount > 0 ? minStringLength : "N/A") +
        "\nMax length: " + (stringCount > 0 ? maxStringLength : "N/A");
  }

  public static String getFullStat() {
    return getShortStat() + "\n" + getIntStat() + "\n" + getFloatStat() + "\n" + getStringStat();
  }

  // Методы для вывода статистики в консоль
  public static void printShortStat() {
    System.out.println(getShortStat());
  }

  public static void printFullStat() {
    System.out.println(getFullStat());
  }
}
