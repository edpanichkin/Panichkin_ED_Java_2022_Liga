package util;

import model.User;

import java.util.List;

public class MessageUtil {

  public static void fileInputError() {
    System.out.println("! Ошибка ввода данных\n! Проверьте путь к файлу");
  }

  public static void workingText() {
    System.out.println("\n....Вычисляю....\n");
  }

  public static void menuPickError() {
    System.out.println("! Выбран не верный пункт меню");
  }

  public static void startMessage() {
    System.out.println("______________________");
    System.out.println("Входные параметры");
  }

  public static void mainMenuMessage() {
    String out = """
            0. Удалить данные и загрузить заново
            1. Вывести весь список пользователей и их задач
            2. Вывести список задач конкретного пользователя
            3. Поменять статус задачи конкретного пользователя
            """;
    System.out.print("\n" + out);
  }

  public static void noSuchElement(String str) {
    System.out.println("Нет такого элемента: " + str + "\n");
  }

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

}

