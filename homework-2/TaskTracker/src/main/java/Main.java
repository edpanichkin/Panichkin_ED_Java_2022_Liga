import exception.FilePathException;
import model.Task;
import model.TaskStatus;
import model.User;
import service.TaskCsvLoad;
import service.UserCsvLoad;
import util.MessageUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
  private static final int MENU_COUNT = 3;
  private static List<User> usersList = new ArrayList<>();
  private static List<Task> taskList = new ArrayList<>();
  private static final Scanner sc = new Scanner(System.in);
  private static final int[] INT_BORDERS_ID = new int[]{0, 127};

  public static void main(String[] args) {
    loadDataToProgram();
    for (; ; ) {
      System.out.println(MessageUtil.mainMenuMessage());
      doMenuItem(scannerMenuInput(0, 3));
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

  public static void doMenuItem(int menu) {
    if (menu > MENU_COUNT) {
      System.out.println(MessageUtil.menuPickError());
      return;
    }
    switch (menu) {
      case 0 -> loadDataToProgram();
      case 1 -> showFullState();
      case 2 -> showUserState();
      case 3 -> changeTaskStatus();
    }
  }

  private static User findUserById(int id) {
    return usersList.stream()
            .filter(user -> user.getId() == id).findAny().orElse(null);
  }

  private static Task findTaskByUserAndId(User user, int id) {
    return user.getTaskList().stream()
            .filter(task -> task.getId() == id).findAny().orElse(null);
  }

  private static void changeTaskStatus() {
    User user = showAvailableUsersAndGetUser();

    if (user != null) {
      MessageUtil.printAvailableId(user.getTaskList().stream().map(Task::getId).toList(), "taskId: ");
      int taskId = scannerMenuInput(INT_BORDERS_ID[0], INT_BORDERS_ID[1]);
      Task task = findTaskByUserAndId(user, taskId);
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
    MessageUtil.printAvailableId(usersList.stream().map(User::getId).toList(), "userId: ");
    int userId = scannerMenuInput(INT_BORDERS_ID[0], INT_BORDERS_ID[1]);
    User user = findUserById(userId);
    if (user == null) {
      System.out.println(MessageUtil.noSuchElement("UserId: " + userId));
      return null;
    }
    return user;
  }

  private static void showUserState() {
    System.out.println(showAvailableUsersAndGetUser());
  }

  private static void showFullState() {
    usersList.forEach(System.out::println);
  }

  private static void loadDataToProgram() {
    if (usersList.size() != 0 && taskList.size() != 0) {
      usersList = new ArrayList<>();
      taskList = new ArrayList<>();
      System.out.println("Данные перезаписаны");
    }
    try {
      String resourcesPath = Stream.of("src", "main", "resources", "")
              .map(String::valueOf)
              .collect(Collectors.joining(File.separator));
      usersList.addAll(new UserCsvLoad().loadFromCsv(resourcesPath + "users.csv"));
      taskList.addAll(new TaskCsvLoad().loadFromCsv(resourcesPath + "tasks.csv"));
    } catch (FilePathException e) {
      System.out.println(MessageUtil.fileInputError());
    }
    matchTaskToUser();
  }

  //n^2
  private static void matchTaskToUser() {
    for (Task task : taskList) {
      usersList.stream()
              .filter(user -> user.getId() == task.getUserId())
              .findAny().ifPresent(user -> user.addTask(task));
    }
  }
}