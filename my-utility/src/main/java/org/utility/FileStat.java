package org.utility;

public class FileStat {

  private static int intCount = 0;
  private static int floatCount = 0;
  private static int stringCount = 0;

  public static void incrementIntCount() {
    intCount++;
  }

  public static void incrementFloatCount() {
    floatCount++;
  }

  public static void incrementStringCount() {
    stringCount++;
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

  public static String getShortStat() {
    return "Integers: " + intCount + "\nFloats: " + floatCount + "\nStrings: " + stringCount;
  }


}

