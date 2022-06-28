package ru.edpanichkin.tasktracker.service.crud;

import ru.edpanichkin.tasktracker.model.Task;
import ru.edpanichkin.tasktracker.model.TaskStatus;
import ru.edpanichkin.tasktracker.model.User;
import ru.edpanichkin.tasktracker.util.MessageUtil;
import ru.edpanichkin.tasktracker.repository.MemoryRepository;
import ru.edpanichkin.tasktracker.util.Menu;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;


public class CrudHelper<T> {

  public static void addUser() {
    System.out.println("addUser");
  }

  public static void deleteUser() {
    System.out.println("deleteUser");
    MemoryRepository.usersMap.remove(showAvailableObjectsAndGetIt(MemoryRepository.usersMap).getId());
  }

  public static void editUser() {
    User user = showAvailableObjectsAndGetIt(MemoryRepository.usersMap);
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
    MemoryRepository.tasksMap.remove(showAvailableObjectsAndGetIt(MemoryRepository.tasksMap).getId());
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
    int objId = Menu.scannerMenuInput(0, maxId.get());
    T obj = map.get(objId);
    if (obj == null) {
      System.out.println(MessageUtil.noSuchElement(map.getClass().getName() + ": " + objId));
      return null;
    }
    return obj;
  }

  public static void showUserState() {
    User user = showAvailableObjectsAndGetIt(MemoryRepository.usersMap);
    if (user != null) {
      System.out.printf("UserId %s: %s", user.getId(), user.getUserName());
      user.getTasksMapInUser().values()
              .stream().sorted(Comparator.comparing(t -> t.getTaskStatus().ordinal()))
              .forEach(System.out::print);
    }
  }

  public static void showFullState() {
    MemoryRepository.usersMap.values().forEach(System.out::println);
  }

  public static void changeTaskStatusById() {
    Task task = showAvailableObjectsAndGetIt(MemoryRepository.tasksMap);
    if (task != null) {
      System.out.println(MessageUtil.taskEnumStatusPrint());
      int taskStatusIndex = Menu.scannerMenuInput(0, TaskStatus.values().length) - 1;
      task.setTaskStatus(TaskStatus.values()[taskStatusIndex]);
      System.out.println(MessageUtil.updateDataMessage());
    } else {
      System.out.println(MessageUtil.noSuchElement("TaskId: "));
    }

  }
}