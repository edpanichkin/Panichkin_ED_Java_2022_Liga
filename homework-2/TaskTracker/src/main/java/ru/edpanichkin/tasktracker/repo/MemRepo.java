package ru.edpanichkin.tasktracker.repo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.edpanichkin.tasktracker.dto.TaskFullDto;
import ru.edpanichkin.tasktracker.dto.UserFullDto;
import ru.edpanichkin.tasktracker.model.Task;
import ru.edpanichkin.tasktracker.model.User;
import ru.edpanichkin.tasktracker.service.TaskRepoService;
import ru.edpanichkin.tasktracker.service.UserRepoService;
import ru.edpanichkin.tasktracker.service.writer.TaskCsvWriter;
import ru.edpanichkin.tasktracker.service.writer.UserCsvWriter;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class MemRepo {

  private static String tasksFilePath;
  private static String usersFilePath;
  private static Map<Integer, UserFullDto> usersMap;
  private static Map<Integer, TaskFullDto> tasksMap;

  private final UserRepoService userRepoService;
  private final TaskRepoService taskRepoService;

  @Autowired
  public MemRepo(@Value("${tasks.file}") String tasksFilePath,
                 @Value("${users.file}") String usersFilePath,
                 UserRepoService userRepoService,
                 TaskRepoService taskRepoService) {
    this.tasksFilePath = tasksFilePath;
    this.usersFilePath = usersFilePath;
    this.userRepoService = userRepoService;
    this.taskRepoService = taskRepoService;

  }

  private String getResourcePath(String filePath) {
    return Stream.of(filePath.split(" "))
            .map(String::valueOf)
            .collect(Collectors.joining(File.separator));
  }

  public void saveState() {
    createUserMap();
    createTaskMap();
    new UserCsvWriter().writeToCsv(usersMap, Path.of(getResourcePath(usersFilePath)));
    new TaskCsvWriter().writeToCsv(tasksMap, Path.of(getResourcePath(tasksFilePath)));
  }

  private void createUserMap() {
    usersMap = new HashMap<>();
    List<User> listUsers = userRepoService.findAll();
    for(User user : listUsers) {
      UserFullDto userFullDto = new UserFullDto(user.getId(), user.getUsername());
      usersMap.put(userFullDto.getId(), userFullDto);
    }
  }

  private void createTaskMap() {
    tasksMap = new HashMap<>();
    List<Task> listTasks = taskRepoService.findAll();
    for(Task task : listTasks) {
      TaskFullDto taskFullDto = new TaskFullDto();
      taskFullDto.setId(task.getId());
      taskFullDto.setTaskName(task.getTaskName());
      taskFullDto.setTaskInfo(task.getTaskInfo());
      taskFullDto.setUserId(task.getUser().getId());
      taskFullDto.setDate(task.getDate());
      taskFullDto.setTaskStatus(task.getTaskStatus());
      tasksMap.put(taskFullDto.getId(), taskFullDto);
    }
  }
}