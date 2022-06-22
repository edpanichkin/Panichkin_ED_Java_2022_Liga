package util;

import java.util.List;

public class MessageUtil {

  public static String fileInputError() {
    return "! Ошибка ввода данных\n! Проверьте путь к файлу";
  }

  public static String menuPickError() {
    return "! Выбран не верный пункт меню";
  }


  public static String mainMenuMessage() {
    return """ 
            \n0. Удалить данные и загрузить заново
            1. Вывести весь список пользователей и их задач
            2. Вывести список задач конкретного пользователя
            3. Поменять статус задачи конкретного пользователя
            """;
  }

  public static String noSuchElement(String str) {
    return "Нет такого элемента: " + str;
  }

  //TODO Переделать тут все / вывод на построчный...и поменять void на String
  public static void printAvailableId(List<Integer> list, String param) {
    int i = 0;
    System.out.println("Доступные " + param);
    for (Integer id : list) {
      System.out.print(id + " ");
      i++;
      if (i > 20) {
        i = 0;
        System.out.println();
      }
    }
    System.out.print("\nВведите " + param);
  }

  public static String taskEnumStatusPrint() {
    return "1. New / 2. In work / 3. Done";
  }

  public static String updateDataMessage() {
    return "Данные обновлены ";
  }
}

