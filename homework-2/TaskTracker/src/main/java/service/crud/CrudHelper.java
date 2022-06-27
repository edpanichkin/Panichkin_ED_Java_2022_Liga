package service.crud;

import model.Task;
import model.TaskStatus;
import model.User;
import util.MessageUtil;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import static repository.MemoryRepository.tasksMap;
import static repository.MemoryRepository.usersMap;
import static util.Menu.scannerMenuInput;

public class CrudHelper<T> {

  public static void addUser() {
    System.out.println("addUser");
  }

  public static void deleteUser() {
    System.out.println("deleteUser");
    usersMap.remove(showAvailableObjectsAndGetIt(usersMap).getId());
  }

  public static void editUser() {
    User user = showAvailableObjectsAndGetIt(usersMap);
    if (user != null) {
      System.out.print(user.getUserName() + " / editUser -> Enter new name: ");
      user.setUserName(askAndGetNewValue());
      System.out.println("new name " + user.getUserName());
    }
  }

  private static String askAndGetNewValue() {
    return new Scanner(System.in).next();
  }

  public static void addTask() {
    System.out.println("addTask");
  }

  public static void deleteTask() {
    System.out.println("deleteTask");
    tasksMap.remove(showAvailableObjectsAndGetIt(tasksMap).getId());
  }

  public static void editTask() {
    System.out.println("editTask");
  }

  public static <T> T showAvailableObjectsAndGetIt(Map<Integer, T> map) {
    System.out.println(MessageUtil.printAvailableId(map, "id"));
    Optional<Integer> maxId = map.keySet().stream().max(Integer::compare);
    if (maxId.isEmpty()) {
      return null;
    }
    int objId = scannerMenuInput(0, maxId.get());
    T obj = map.get(objId);
    if (obj == null) {
      System.out.println(MessageUtil.noSuchElement(map.getClass().getName() + ": " + objId));
      return null;
    }
    return obj;
  }

  public static void showUserState() {
    User user = showAvailableObjectsAndGetIt(usersMap);
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

  public static void changeTaskStatusById() {
    Task task = showAvailableObjectsAndGetIt(tasksMap);
    if (task != null) {
      System.out.println(MessageUtil.taskEnumStatusPrint());
      int taskStatusIndex = scannerMenuInput(0, TaskStatus.values().length) - 1;
      task.setTaskStatus(TaskStatus.values()[taskStatusIndex]);
      System.out.println(MessageUtil.updateDataMessage());
    } else {
      System.out.println(MessageUtil.noSuchElement("TaskId: "));
    }

  }
}