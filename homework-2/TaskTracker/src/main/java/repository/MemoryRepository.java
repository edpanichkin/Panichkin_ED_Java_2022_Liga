package repository;

import exception.FilePathException;
import model.Task;
import model.TaskStatus;
import model.User;
import service.reader.TaskCsvReader;
import service.reader.UserCsvReader;
import service.writer.TaskCsvWriter;
import service.writer.UserCsvWriter;
import util.MessageUtil;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MemoryRepository {
  public static Map<Integer, User> usersMap = new HashMap<>();
  public static Map<Integer, Task> tasksMap = new HashMap<>();

  public static void saveState() {
    new UserCsvWriter().writeToCsv(usersMap, Paths.get("src", "main", "resources", "writeUsers.csv"));
    new TaskCsvWriter().writeToCsv(tasksMap, Paths.get("src", "main", "resources", "writeTasks.csv"));
  }

  public static void loadDataToProgram() {
    if (usersMap.size() != 0 && tasksMap.size() != 0) {
      usersMap = new HashMap<>();
      tasksMap = new HashMap<>();
      System.out.println(MessageUtil.updateDataMessage());
    }
    try {
      String resourcesPath = Stream.of("src", "main", "resources", "")
              .map(String::valueOf)
              .collect(Collectors.joining(File.separator));
      usersMap.putAll(new UserCsvReader().loadFromCsv(resourcesPath + "users.csv"));
      tasksMap.putAll(new TaskCsvReader().loadFromCsv(resourcesPath + "tasks.csv"));
    } catch (FilePathException e) {
      System.out.println(MessageUtil.fileInputError());
    }
    for (Task task : tasksMap.values()) {
      usersMap.get(task.getUserId()).putTask(task);
    }
  }
}
