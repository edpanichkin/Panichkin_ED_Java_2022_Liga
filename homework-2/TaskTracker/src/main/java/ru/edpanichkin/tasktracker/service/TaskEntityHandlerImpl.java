package ru.edpanichkin.tasktracker.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.edpanichkin.tasktracker.model.EntityType;
import ru.edpanichkin.tasktracker.model.Task;
import ru.edpanichkin.tasktracker.model.TaskStatus;
import ru.edpanichkin.tasktracker.model.User;
import ru.edpanichkin.tasktracker.repo.MemRepo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;

@Component
@Slf4j
public class TaskEntityHandlerImpl implements EntityHandler<Task> {

  private static final int TASK_ID_POS = 2;
  private static final int EDIT_TASK_STATUS_POS = 3;
  private static final int ADD_TASK_NAME_POS = 2;
  private static final int ADD_TASK_INFO_POS = 3;
  private static final int ADD_USER_ID_POS = 4;
  private static final int ADD_DATE_POS = 5;

  private static final Map<Integer, Task> tasksMap = MemRepo.getEntityMap(EntityType.TASK);

  @Override
  public EntityType getType() {
    return EntityType.TASK;
  }

  @Override
  public String view(String[] command) {
    int taskId = Integer.parseInt(command[TASK_ID_POS]);
    return tasksMap.get(taskId).toString();
  }

  @Override
  public String add(String[] command) {
    int id = MemRepo.getNextId(EntityType.TASK);
    String taskName = command[ADD_TASK_NAME_POS];
    String taskInfo = command[ADD_TASK_INFO_POS];
    int userId = Integer.parseInt(command[ADD_USER_ID_POS]);
    LocalDate date = LocalDate.parse(command[ADD_DATE_POS], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    Task task = new Task(id, taskName, taskInfo, userId, date, TaskStatus.NEW);
    tasksMap.put(id, task);
    return "Task added / id: " + id;
  }

  @Override
  public String delete(String[] command) {
    int taskId = Integer.parseInt(command[TASK_ID_POS]);
    int userId = getUserIdOwner(taskId);
    if (userId == -1) {
      return "ERROR ID";
    }
    Map<Integer, User> usersMap = MemRepo.getEntityMap(EntityType.USER);
    usersMap.get(userId).getTasksMapInUser().remove(taskId);
    tasksMap.remove(taskId);
    return "DELETED TASK " + taskId + " and in userId " + userId;
  }

  @Override
  public String edit(String[] command) {
    int taskId = Integer.parseInt(command[TASK_ID_POS]);
    TaskStatus taskStatus = TaskStatus.values()[Integer.parseInt(command[EDIT_TASK_STATUS_POS])];
    tasksMap.get(taskId).setTaskStatus(taskStatus);
    return "TASK STATUS " + taskId + " changed to " + taskStatus;
  }

  @Override
  public Map<Integer, Task> getMap() {
    return tasksMap;
  }

  public int getUserIdOwner(int taskId) {
    int userId;
    try {
      userId = tasksMap.get(taskId).getUserId();
    } catch (NullPointerException exception) {
      return -1;
    }
    return userId;
  }
}