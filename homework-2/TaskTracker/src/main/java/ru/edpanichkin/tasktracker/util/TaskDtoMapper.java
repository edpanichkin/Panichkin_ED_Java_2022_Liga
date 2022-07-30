package ru.edpanichkin.tasktracker.util;

import ru.edpanichkin.tasktracker.dto.TaskFullDto;
import ru.edpanichkin.tasktracker.model.Task;

public class TaskDtoMapper {
  public static TaskFullDto queryEntityToDto(Task task) {
    TaskFullDto TaskFullDto = new TaskFullDto();
    TaskFullDto.setId(task.getId());
    TaskFullDto.setUserId(task.getUser().getId());
    TaskFullDto.setTaskName(task.getTaskName());
    TaskFullDto.setTaskInfo(task.getTaskInfo());
    TaskFullDto.setTaskStatus(task.getTaskStatus());
    TaskFullDto.setDate(task.getDate());
    return TaskFullDto;
  }
}
