package ru.edpanichkin.tasktracker.util;

public class MessageUtil {

  public static String fileInputError() {
    return "! Ошибка ввода данных\n! Проверьте путь к файлу";
  }

  public static String updateDataMessage() {
    return "Данные обновлены";
  }

  public static String saveDataMessage() {
    return "Данные сохранены";
  }

  public static String cleanedMemoryMessage() {
    return "Memory db was cleaned";
  }

  public static String loadDataMessage() {
    return "DATA was loaded to Memory db";
  }

  public static String errorInArgs() {
    return "ERROR IN ARGS";
  }

  public static String shutDownMessage() {
    return "Shutdown the System";
  }
}

