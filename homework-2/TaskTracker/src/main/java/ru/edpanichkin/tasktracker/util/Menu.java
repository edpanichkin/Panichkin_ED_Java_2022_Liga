//package ru.edpanichkin.tasktracker.util;
//
//import java.util.Scanner;
//
//public class Menu {
//  private static final int MENU_COUNT = 6;
//  private static final int CRUD_MENU_COUNT = 7;
//  private static final Scanner sc = new Scanner(System.in);

//  public static void mainMenu() {
//    System.out.println(MessageUtil.mainMenuMessage());
//    doMainMenuItem(scannerMenuInput(0, MENU_COUNT));
//  }
//
//  private static void doMainMenuItem(int selectedMenuItem) {
//    System.out.println(MessageUtil.mainMenuMessage());
//    if (selectedMenuItem > MENU_COUNT) {
//      System.out.println(MessageUtil.menuPickError());
//      return;
//    }
//    switch (selectedMenuItem) {
//      case 0 -> loadDataToProgram();
//      case 1 -> showFullState();
//      case 2 -> showUserState();
//      case 3 -> changeTaskStatusById();
//      case 4 -> crudMenu();
//      case 5 -> saveState();
//      case 6 -> System.exit(0);
//    }
//  }
//
//  private static void crudMenu() {
//    System.out.println(MessageUtil.crudMenuMessage());
//    doCrudMenuItem(scannerMenuInput(1, CRUD_MENU_COUNT));
//  }
//
//  private static void doCrudMenuItem(int selectedMenuItem) {
//    if (selectedMenuItem > CRUD_MENU_COUNT) {
//      System.out.println(MessageUtil.menuPickError());
//      crudMenu();
//      return;
//    }
//    switch (selectedMenuItem) {
//      case 1 -> CrudHelper.addUser();
//      case 2 -> CrudHelper.editUser();
//      case 3 -> CrudHelper.deleteUser();
//      case 4 -> CrudHelper.addTask();
//      case 5 -> CrudHelper.editTask();
//      case 6 -> CrudHelper.deleteTask();
//      case 7 -> mainMenu();
//    }
//  }

//  //TODO более универсальное решение
//  public static int scannerMenuInput(int leftIndex, int rightIndex) {
//    int number;
//    do {
//      System.out.println("Введите значение в рамках предложенных");
//      while (!sc.hasNextInt()) {
//        System.out.println(MessageUtil.menuPickError());
//        System.out.println(MessageUtil.mainMenuMessage());
//        sc.next();
//      }
//      number = sc.nextInt();
//    } while (number < leftIndex || number > rightIndex);
//    return number;
//  }
//}