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

  public static void main(String[] args) {
    loadDataToProgram();

    Scanner sc = new Scanner(System.in);
    for (; ; ) {
      MessageUtil.mainMenuMessage();
      doMenuItem(scannerMenuInput(sc));
    }
  }

  private static int scannerMenuInput(Scanner sc) {
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
      case 3 -> changeTaskStatus(1, 3, TaskStatus.IN_WORK);
    }
  }

  private static void saveProgramState() {
  }

  private static void changeTaskStatus(int userId, int taskId, TaskStatus taskStatus) {
    Optional<Task> optionalTask = usersList.stream()
            .filter(user -> user.getId() == userId)
            .findAny()
            .get()
            .getTaskList().stream()
            .filter(task -> task.getId() == taskId)
            .findAny();
    if (optionalTask.isPresent()) {

      optionalTask.get().setTaskStatus(taskStatus);
    } else {
      MessageUtil.noSuchElement();
    }
  }

  private static void showUserState() {
  }

  private static void showFullState() {
    usersList.forEach(System.out::println);
  }

  private static void loadDataToProgram() {
    if(usersList.size() != 0 && taskList.size() != 0) {
      usersList = new ArrayList<>();
      taskList = new ArrayList<>();
      System.out.println("Данные перезаписаны");
    }
    try {
      usersList.addAll(new UserCsvLoad().loadFromCsv("src\\main\\resources\\users.csv"));
      taskList.addAll(new TaskCsvLoad().loadFromCsv("src\\main\\resources\\tasks.csv"));

      //
    } catch (FilePathException e) {
      System.out.println("Не смогла");
    }
    matchTaskToUser();
  }

  private static void matchTaskToUser() {
    for (Task task : taskList) {
      usersList.stream()
              .filter(user -> user.getId() == task.getUserId())
              .findAny()
              .get()
              .addTask(task);
    }
  }
}
