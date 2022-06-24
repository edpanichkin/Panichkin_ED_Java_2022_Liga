package util;

import service.crud.CrudHelper;

import java.util.Scanner;

import static repository.MemoryRepository.*;
import static service.crud.CrudHelper.*;

public class Menu {
  private static final int MENU_COUNT = 6;
  private static final Scanner sc = new Scanner(System.in);

  public static void mainMenu() {
    System.out.println(MessageUtil.mainMenuMessage());
    doMainMenuItem(scannerMenuInput(0, 6));
  }

  private static void doMainMenuItem(int selectedMenuItem) {
    System.out.println(MessageUtil.mainMenuMessage());
    if (selectedMenuItem > MENU_COUNT) {
      System.out.println(MessageUtil.menuPickError());
      return;
    }
    switch (selectedMenuItem) {
      case 0 -> loadDataToProgram();
      case 1 -> showFullState();
      case 2 -> showUserState();
      case 3 -> changeTaskStatus();
      case 4 -> crudMenu();
      case 5 -> saveState();
      case 6 -> System.exit(0);
    }
  }

  private static void crudMenu() {
    System.out.println(MessageUtil.crudMenuMessage());
    doCrudMenuItem(scannerMenuInput(1, 7));
  }

  private static void doCrudMenuItem(int selectedMenuItem) {
    if (selectedMenuItem > 6) {
      System.out.println(MessageUtil.menuPickError());
      return;
    }
    switch (selectedMenuItem) {
      case 0 -> CrudHelper.addUser();
      case 1 -> CrudHelper.editUser();
      case 2 -> CrudHelper.deleteUser();
      case 3 -> CrudHelper.addTask();
      case 4 -> CrudHelper.editTask();
      case 5 -> CrudHelper.deleteTask();
      case 6 -> mainMenu();
    }
  }

  //TODO более универсальное решение
  public static int scannerMenuInput(int leftIndex, int rightIndex) {
    int number;
    do {
      System.out.println("Введите значение в рамках предложенных");
      while (!sc.hasNextInt()) {
        System.out.println(MessageUtil.menuPickError());
        System.out.println(MessageUtil.mainMenuMessage());
        sc.next();
      }
      number = sc.nextInt();
    } while (number < leftIndex || number > rightIndex);
    return number;
  }
}