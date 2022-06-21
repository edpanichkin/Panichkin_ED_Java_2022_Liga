import exception.FilePathException;
import model.Task;
import model.TaskStatus;
import model.User;
import service.TaskCsvLoad;
import service.UserCsvLoad;
import util.MessageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
  private static final int MENU_COUNT = 3;
  private static List<User> usersList = new ArrayList<>();
  private static List<Task> taskList = new ArrayList<>();
  private static final Scanner sc = new Scanner(System.in);

  static {
    loadDataToProgram();
  }

  public static void main(String[] args) {
    for (; ; ) {
      MessageUtil.mainMenuMessage();
      doMenuItem(scannerMenuInput());
    }
  }

  private static int scannerMenuInput() {
    int number;
    do {
      while (!sc.hasNextInt()) {
        MessageUtil.menuPickError();
        MessageUtil.mainMenuMessage();
        sc.next();
      }
      number = sc.nextInt();
      if (number < 0 || number > MENU_COUNT) {
        MessageUtil.menuPickError();
        MessageUtil.mainMenuMessage();
      }
    } while (number < 0 || number > MENU_COUNT);
    return number;
  }

  public static void doMenuItem(int menu) {
    switch (menu) {
      case 0 -> loadDataToProgram();
      case 1 -> showFullState();
      case 2 -> showUserState();
      case 3 -> changeTaskStatus();
    }
  }


  private static User findUserById(int id) {
    Optional<User> optionalUser = usersList.stream().filter(user -> user.getId() == id).findAny();
    return optionalUser.orElse(null);
  }

  private static Task findTaskByUserAndId(User user, int id) {
    Optional<Task> optionalTask = user.getTaskList().stream().filter(task -> task.getId() == id).findAny();
    return optionalTask.orElse(null);
  }

  private static void changeTaskStatus() {
    TaskStatus taskStatus = TaskStatus.DONE;
    User user = showAvailableUsersAndGetUser();

    if (user != null) {
      MessageUtil.printAvailableId(user.getTaskList().stream().map(Task::getId).toList(), "taskId: ");
      int taskId = sc.nextInt();
      Task task = findTaskByUserAndId(user, taskId);
      if (task != null) {
        task.setTaskStatus(taskStatus);
      } else {
        MessageUtil.noSuchElement("TaskId: " + taskId);
      }
    }
  }

  private static User showAvailableUsersAndGetUser() {
    MessageUtil.printAvailableId(usersList.stream().map(User::getId).toList(), "userId: ");
    int userId = sc.nextInt();
    User user = findUserById(userId);
    if (user == null) {
        MessageUtil.noSuchElement("UserId: " + userId);
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
      usersList.addAll(new UserCsvLoad().loadFromCsv("src\\main\\resources\\users.csv"));
      taskList.addAll(new TaskCsvLoad().loadFromCsv("src\\main\\resources\\tasks.csv"));

    } catch (FilePathException e) {
      System.out.println("Не смогла");
    }
    matchTaskToUser();
  }

  private static void matchTaskToUser() {
    for (Task task : taskList) {
      usersList.stream()
              .filter(user -> user.getId() == task.getUserId())
              .findAny().ifPresent(user -> user.addTask(task));
    }
  }
}
