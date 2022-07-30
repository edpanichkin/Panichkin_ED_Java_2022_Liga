package ru.edpanichkin.tasktracker.util;

import ru.edpanichkin.tasktracker.dto.UserWithTasksDto;
import ru.edpanichkin.tasktracker.model.Task;
import ru.edpanichkin.tasktracker.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDtoMapper {

  public static UserWithTasksDto queryEntityToDto(User user) {
    UserWithTasksDto userWithTasksDto = new UserWithTasksDto();
    userWithTasksDto.setId(user.getId());
    userWithTasksDto.setUserName(user.getUserName());
    userWithTasksDto.setTasks(user.getTaskList().stream()
            .map(TaskDtoMapper::queryEntityToDto)
            .collect(Collectors.toList()));

    return userWithTasksDto;
  }

  public static UserWithTasksDto queryUserTaskToUserDto(User user, List<Task> taskList) {
    if (user == null) {
      return null;
    }
    UserWithTasksDto userWithTasksDto = new UserWithTasksDto();
    userWithTasksDto.setId(user.getId());
    userWithTasksDto.setUserName(user.getUserName());
    userWithTasksDto.setTasks(taskList.stream().map(TaskDtoMapper::queryEntityToDto).collect(Collectors.toList()));
    return userWithTasksDto;
  }
}
