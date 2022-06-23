import exception.FilePathException;
import model.Task;
import model.TaskStatus;
import model.User;
import service.TaskCsvLoad;
import service.UserCsvLoad;
import util.MessageUtil;

import java.util.*;
import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
  private static final int MENU_COUNT = 4;

  private static Map<Integer, User> usersMap = new HashMap<>();
  private static Map<Integer, Task> tasksMap = new HashMap<>();
  private static final Scanner sc = new Scanner(System.in);
  private static final Integer[] INT_BORDERS_ID = new Integer[]{0, Integer.MAX_VALUE};

  public static void main(String[] args) {
    loadDataToProgram();
    for (; ; ) {
      System.out.println(MessageUtil.mainMenuMessage());
      doMenuItem(scannerMenuInput(0, MENU_COUNT));
    }
  }

  private static int scannerMenuInput(int leftIndex, int rightIndex) {
    int number;
    do {
      while (!sc.hasNextInt()) {
        System.out.println(MessageUtil.menuPickError());
        System.out.println(MessageUtil.mainMenuMessage());
        sc.next();
      }
      number = sc.nextInt();
    } while (number < leftIndex || number > rightIndex);
    return number;
  }

  public static void doMenuItem(int selectedMenuItem) {
    if (selectedMenuItem > MENU_COUNT) {
      System.out.println(MessageUtil.menuPickError());
      return;
    }
    switch (selectedMenuItem) {
      case 0 -> loadDataToProgram();
      case 1 -> showFullState();
      case 2 -> showUserState();
      case 3 -> changeTaskStatus();
      case 4 -> System.exit(0);
    }
  }

  private static void changeTaskStatus() {
    User user = showAvailableUsersAndGetUser();
    if (user != null) {
      System.out.println(MessageUtil.printAvailableId(user.getTasksMapInUser(), "taskId: "));
      int taskId = scannerMenuInput(INT_BORDERS_ID[0], INT_BORDERS_ID[1]);
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
    int userId = scannerMenuInput(INT_BORDERS_ID[0], INT_BORDERS_ID[1]);
    User user = usersMap.get(userId);
    if (user == null) {
      System.out.println(MessageUtil.noSuchElement("UserId: " + userId));
      return null;
    }
    return user;
  }

  private static void showUserState() {
    User user = showAvailableUsersAndGetUser();
    System.out.print(user == null ? "" : user.toString());
  }

  private static void showFullState() {
    usersMap.values().forEach(System.out::println);
  }

  private static void loadDataToProgram() {
    if (usersMap.size() != 0 && tasksMap.size() != 0) {
      usersMap = new HashMap<>();
      tasksMap = new HashMap<>();
      System.out.println("Данные перезаписаны");
    }
    try {
      String resourcesPath = Stream.of("src", "main", "resources", "")
              .map(String::valueOf)
              .collect(Collectors.joining(File.separator));
      usersMap.putAll(new UserCsvLoad().loadFromCsv(resourcesPath + "users.csv"));
      tasksMap.putAll(new TaskCsvLoad().loadFromCsv(resourcesPath + "tasks.csv"));
    } catch (FilePathException e) {
      System.out.println(MessageUtil.fileInputError());
    }
    for (Task task : tasksMap.values()) {
      usersMap.get(task.getUserId()).putTask(task);
    }
  }
}