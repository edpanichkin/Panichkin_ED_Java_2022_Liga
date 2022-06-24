package service.crud;

import model.Task;
import model.TaskStatus;
import model.User;
import util.MessageUtil;

import java.util.Comparator;
import java.util.Optional;

import static repository.MemoryRepository.tasksMap;
import static repository.MemoryRepository.usersMap;
import static util.Menu.scannerMenuInput;

public class CrudHelper {

  public static void addUser() {
    System.out.println("addUser");
  }

  public static void deleteUser() {
    System.out.println("deleteUser");
  }

  public static void editUser() {
    System.out.println("editUser");
  }

  public static void addTask() {
    System.out.println("addTask");
  }

  public static void deleteTask() {
    System.out.println("deleteTask");
  }

  public static void editTask() {
    System.out.println("editTask");
  }

  public static User showAvailableUsersAndGetUser() {
    System.out.println(MessageUtil.printAvailableId(usersMap, "userId: "));
    Optional<Integer> maxId = usersMap.keySet().stream().max(Integer::compare);
    if (maxId.isPresent()) {
      int userId = scannerMenuInput(0, maxId.get());
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

  public static void showUserState() {
    User user = showAvailableUsersAndGetUser();
    if (user != null) {
      System.out.printf("UserId %s: %s", user.getId(), user.getUserName());
      user.getTasksMapInUser().values()
              .stream().sorted(Comparator.comparing(t -> t.getTaskStatus().ordinal()))
              .forEach(System.out::print);
    }
  }

  public static void showFullState() {
    usersMap.values().forEach(System.out::println);
  }

  public static void changeTaskStatus() {
    User user = showAvailableUsersAndGetUser();
    if (user != null) {
      System.out.println(MessageUtil.printAvailableId(user.getTasksMapInUser(), "taskId: "));
      int taskId = scannerMenuInput(0, tasksMap.keySet().stream().max(Integer::compare).get());
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
}