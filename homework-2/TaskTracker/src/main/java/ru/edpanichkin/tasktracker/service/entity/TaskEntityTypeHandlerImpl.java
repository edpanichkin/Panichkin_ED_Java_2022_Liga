package ru.edpanichkin.tasktracker.service.entity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.edpanichkin.tasktracker.dto.TaskFullDto;
import ru.edpanichkin.tasktracker.model.EntityType;
import ru.edpanichkin.tasktracker.model.Task;
import ru.edpanichkin.tasktracker.model.TaskStatus;
import ru.edpanichkin.tasktracker.service.TaskRepoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class TaskEntityTypeHandlerImpl implements EntityTypeHandler<Task> {

  private static final int TASK_ID_POS = 2;
  private static final int EDIT_TASK_STATUS_POS = 3;
  private static final int ADD_TASK_NAME_POS = 2;
  private static final int ADD_TASK_INFO_POS = 3;
  private static final int ADD_USER_ID_POS = 4;
  private static final int ADD_DATE_POS = 5;
  private static final int ADD_TASK_STATUS_POS = 6;
  private static final int ADD_TASK_WITH_STATUS = 7;

  private final TaskRepoService taskRepoService;

  @Override
  public EntityType getType() {
    return EntityType.TASK;
  }

  @Override
  public String view(String[] command) {
    int taskId = Integer.parseInt(command[TASK_ID_POS]);
    Task task = taskRepoService.getById(taskId);
    return task == null ? "TASK ID ERROR" : task.toString();
  }

  @Override
  @Transactional
  public String add(String[] command) {
    TaskFullDto taskDto = new TaskFullDto();
    taskDto.setTaskName(command[ADD_TASK_NAME_POS]);
    taskDto.setTaskInfo(command[ADD_TASK_INFO_POS]);
    taskDto.setUserId(Integer.parseInt(command[ADD_USER_ID_POS]));
    taskDto.setDate(LocalDate.parse(command[ADD_DATE_POS], DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    TaskStatus taskStatus = command.length == ADD_TASK_WITH_STATUS
            ? TaskStatus.values()[Integer.parseInt(command[ADD_TASK_STATUS_POS])]
            : TaskStatus.NEW;
    taskDto.setTaskStatus(taskStatus);
    return "Task added / id: " + taskRepoService.add(taskDto);
  }

  @Override
  public String delete(String[] command) {
    int taskId = Integer.parseInt(command[TASK_ID_POS]);
    taskRepoService.delete(taskId);
    return "DELETED TASK " + taskId;
  }

  @Override
  @Transactional
  public String edit(String[] command) {
    int taskId = Integer.parseInt(command[TASK_ID_POS]);
    int taskStatusInputNumber = Integer.parseInt(command[EDIT_TASK_STATUS_POS]);
    if (taskStatusInputNumber < 0 || taskStatusInputNumber > 2) {
      return "TaskStatus number ERROR";
    }
    TaskStatus taskStatus = TaskStatus.values()[taskStatusInputNumber];
    String result = taskRepoService.edit(taskId, taskStatus);
    return Objects.equals(result, "UPDATE") ? "TASK STATUS " + taskId + " changed to " + taskStatus : "ERROR";
  }
}