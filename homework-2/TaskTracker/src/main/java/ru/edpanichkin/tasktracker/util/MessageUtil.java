package ru.edpanichkin.tasktracker.util;

import java.util.Map;

public class MessageUtil {

  public static String fileInputError() {
    return "! Ошибка ввода данных\n! Проверьте путь к файлу";
  }

  public static String menuPickError() {
    return "! Выбран не верный пункт меню";
  }

  public static String mainMenuMessage() {
    return "MENU";
//    return """
//            \n0. Удалить данные и/или загрузить заново
//            1. Вывести весь список пользователей и их задач
//            2. Вывести список задач конкретного пользователя
//            3. Поменять статус конкретной задачи
//            4. CRUD меню
//            5. Сохранить состояние программы
//            6. Выйти из программы
//            """;
  }

  public static String crudMenuMessage() {
    return "CRUD_MENU";
//    return """
//            \n--1. Добавить пользователя
//            --2. Редактировать пользователя
//            --3. Удалить пользователя
//            --4. Добавить задачу
//            --5. Редактировать задачу
//            --6. Удалить задачу
//            --7. Главное меню
//            """;
  }

  public static String noSuchElement(String str) {
    return "Нет такого элемента: " + str;
  }

  //TODO Переделать тут все / вывод на построчный...и поменять void на String
  public static <T> String printAvailableId(Map<Integer, T> map, String param) {
    StringBuilder stringBuilder = new StringBuilder("Доступные " + param + "\n");
    for (Integer item : map.keySet()) {
      stringBuilder.append(item).append("\n");
    }
    return stringBuilder.append("Введите ").append(param).toString();
  }

  public static String taskEnumStatusPrint() {
    return "1. New / 2. In work / 3. Done";
  }

  public static String updateDataMessage() {
    return "Данные обновлены ";
  }
}

