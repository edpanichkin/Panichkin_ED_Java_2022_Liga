import exception.FilePathException;
import model.Task;
import model.TaskStatus;
import model.User;
import service.crud.TaskCrudHelper;
import service.crud.UserCrudHelper;
import service.reader.TaskCsvReader;
import service.writer.TaskCsvWriter;
import service.reader.UserCsvReader;
import service.writer.UserCsvWriter;
import util.MessageUtil;

import java.nio.file.Paths;
import java.util.*;
import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
  private static final int MENU_COUNT = 6;

  private static Map<Integer, User> usersMap = new HashMap<>();
  private static Map<Integer, Task> tasksMap = new HashMap<>();
  private static final Scanner sc = new Scanner(System.in);
  private static final Integer[] INT_BORDERS_ID = new Integer[]{0, Integer.MAX_VALUE};

  public static void main(String[] args) {
    loadDataToProgram();
    while (true) {
      mainMenu();
    }
  }

  //TODO более универсальное решение
  private static int scannerMenuInput(int leftIndex, int rightIndex) {
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

  private static void mainMenu() {
    System.out.println(MessageUtil.mainMenuMessage());
    doMainMenuItem(scannerMenuInput(0, 6));

  }

  public static void doMainMenuItem(int selectedMenuItem) {
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

  private static void saveState() {
    new UserCsvWriter().writeToCsv(usersMap, Paths.get("src", "main", "resources", "writeUsers.csv"));
    new TaskCsvWriter().writeToCsv(tasksMap, Paths.get("src", "main", "resources", "writeTasks.csv"));
  }

  private static void crudMenu() {
    System.out.println(MessageUtil.crudMenuMessage());
    doCrudMenuItem(scannerMenuInput(1, 7));
  }

  public static void doCrudMenuItem(int selectedMenuItem) {
    if (selectedMenuItem > 6) {
      System.out.println(MessageUtil.menuPickError());
      return;
    }
    switch (selectedMenuItem) {
      case 0 -> UserCrudHelper.addUser();
      case 1 -> UserCrudHelper.editUser();
      case 2 -> UserCrudHelper.deleteUser();
      case 3 -> TaskCrudHelper.addTask();
      case 4 -> TaskCrudHelper.editTask();
      case 5 -> TaskCrudHelper.deleteTask();
      case 6 -> mainMenu();
    }
  }

  private static void changeTaskStatus() {
    User user = showAvailableUsersAndGetUser();
    if (user != null) {
      System.out.println(MessageUtil.printAvailableId(user.getTasksMapInUser(), "taskId: "));
      int taskId = scannerMenuInput(INT_BORDERS_ID[0], tasksMap.keySet().stream().max(Integer::compare).get());
      Task task = usersMap.get(user.getId()).getTasksMapInUser().get(taskId);
      if (task != null) {
        System.out.println(MessageUtil.taskEnumStatusPrint());
        int taskStatusIndex = scannerMenuInput(0, TaskStatus.values().length) - 1;
        task.setTaskStatus(TaskStatus.values()[taskStatusIndex]);
        System.out.println(MessageUtil.updateDataMessage());
      } else {
        System.out.println(MessageUtil.noSuchElement("TaskId: " + taskId));
      }
    }
  }

  private static User showAvailableUsersAndGetUser() {
    System.out.println(MessageUtil.printAvailableId(usersMap, "userId: "));
    Optional<Integer> maxId = usersMap.keySet().stream().max(Integer::compare);
    if (maxId.isPresent()) {
      int userId = scannerMenuInput(INT_BORDERS_ID[0], maxId.get());
      User user = usersMap.get(userId);
      if (user == null) {
        System.out.println(MessageUtil.noSuchElement("UserId: " + userId));
        return null;
      }
      return user;
    } else {
      return null;
    }
  }

  private static void showUserState() {
    User user = showAvailableUsersAndGetUser();
    if (user != null) {
      System.out.printf("UserId %s: %s", user.getId(), user.getUserName());
      user.getTasksMapInUser().values()
              .stream().sorted(Comparator.comparing(t -> t.getTaskStatus().ordinal()))
              .forEach(System.out::print);
    }
  }

  private static void showFullState() {
    usersMap.values().forEach(System.out::println);
  }

  private static void loadDataToProgram() {
    if (usersMap.size() != 0 && tasksMap.size() != 0) {
      usersMap = new HashMap<>();
      tasksMap = new HashMap<>();
      System.out.println(MessageUtil.updateDataMessage());
    }
    try {
      String resourcesPath = Stream.of("src", "main", "resources", "")
              .map(String::valueOf)
              .collect(Collectors.joining(File.separator));
      usersMap.putAll(new UserCsvReader().loadFromCsv(resourcesPath + "users.csv"));
      tasksMap.putAll(new TaskCsvReader().loadFromCsv(resourcesPath + "tasks.csv"));
    } catch (FilePathException e) {
      System.out.println(MessageUtil.fileInputError());
      //e.printStackTrace();
    }
    for (Task task : tasksMap.values()) {
      usersMap.get(task.getUserId()).putTask(task);
    }
  }
}