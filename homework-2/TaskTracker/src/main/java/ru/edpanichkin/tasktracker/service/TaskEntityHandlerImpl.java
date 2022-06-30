package ru.edpanichkin.tasktracker.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.edpanichkin.tasktracker.model.EntityType;
import ru.edpanichkin.tasktracker.model.Task;
import ru.edpanichkin.tasktracker.model.TaskStatus;
import ru.edpanichkin.tasktracker.repo.MemRepo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

@Component
@Slf4j
public class TaskEntityHandlerImpl implements EntityHandler<Task> {
  @Override
  public EntityType getType() {
    return EntityType.TASK;
  }

  @Override
  public String view(String[] command) {
    log.error("TASK view: " + Arrays.toString(command));
    return MemRepo.tasksMap.get(Integer.parseInt(command[2])).toString();
  }

  @Override
  public String add(String[] command) {
    log.error("TASK add: " + Arrays.toString(command));
    int id = 0;
    Optional<Integer> maxId = MemRepo.tasksMap.keySet().stream().max(Integer::compare);
    if (maxId.isPresent()) {
      id = maxId.get() + 1;
      log.error("MAXID " + maxId.get());
    }
    // 0    1     2         3       4     5     6
    //add task taskName taskInfo userId date taskStatus
    //add task Spring Внесена_из_Веба 3 29.07.2022
    Task task = new Task(id,
            command[2],
            command[3],
            Integer.parseInt(command[4]),
            LocalDate.parse(command[5], DateTimeFormatter.ofPattern("dd.MM.yyyy")),
            TaskStatus.NEW);
    MemRepo.tasksMap.put(id, task);
    return "Task added / id: " + id;
  }

  @Override
  public String delete(String[] command) {
    log.error("TASK delete: " + Arrays.toString(command));
    int taskId = Integer.parseInt(command[2]);
    int userId = MemRepo.tasksMap.get(taskId).getUserId();
    MemRepo.usersMap.get(userId).getTasksMapInUser().remove(taskId);
    MemRepo.tasksMap.remove(taskId);
    return "DELETED TASK " + taskId + " and in userId " + userId;
  }

  @Override
  public String edit(String[] command) {
    log.error("TASK edit: " + Arrays.toString(command));
    int taskId = Integer.parseInt(command[2]);
    TaskStatus taskStatus = TaskStatus.values()[Integer.parseInt(command[3])];
    MemRepo.tasksMap.get(taskId).setTaskStatus(taskStatus);
    return "TASK STATUS " + taskId + " changed to " + taskStatus;
  }
}
