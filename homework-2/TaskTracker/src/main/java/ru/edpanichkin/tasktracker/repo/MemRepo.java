package ru.edpanichkin.tasktracker.repo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.edpanichkin.tasktracker.exception.FilePathException;
import ru.edpanichkin.tasktracker.model.Task;
import ru.edpanichkin.tasktracker.model.User;

import ru.edpanichkin.tasktracker.service.reader.CsvReader;
import ru.edpanichkin.tasktracker.service.reader.TaskCsvReader;
import ru.edpanichkin.tasktracker.service.reader.UserCsvReader;
import ru.edpanichkin.tasktracker.service.writer.TaskCsvWriter;
import ru.edpanichkin.tasktracker.service.writer.UserCsvWriter;
import ru.edpanichkin.tasktracker.util.MessageUtil;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MemRepo {

  public static String tasksFilePath;
  public static String usersFilePath;
  public static String tasksWriteFilePath;
  public static String usersWriteFilePath;
  public static Map<Integer, User> usersMap = new HashMap<>();
  public static Map<Integer, Task> tasksMap = new HashMap<>();

  public MemRepo(@Value("${tasks.load.file}") String tasksFilePath,
                 @Value("${users.load.file}") String usersFilePath,
                 @Value("${tasks.write.file}") String tasksWriteFilePath,
                 @Value("${users.write.file}") String usersWriteFilePath) {
    this.tasksFilePath = tasksFilePath;
    this.usersFilePath = usersFilePath;
    this.tasksWriteFilePath = tasksWriteFilePath;
    this.usersWriteFilePath = usersWriteFilePath;
  }

  public static void saveState() {
    new UserCsvWriter().writeToCsv(usersMap, Path.of(getResourcePath(usersWriteFilePath)));
    new TaskCsvWriter().writeToCsv(tasksMap, Path.of(getResourcePath(tasksWriteFilePath)));
  }

  public static void loadDataToProgram() {
    cleanMemoryData();
    readFromFiles(usersMap, usersFilePath, new UserCsvReader());
    readFromFiles(tasksMap, tasksFilePath, new TaskCsvReader());
    combineUsersAndTasks();
  }

  private static <T> void readFromFiles(Map<Integer, T> map, String filePath, CsvReader csvReader) {
    try {
      map.putAll(csvReader.loadFromCsv(getResourcePath(filePath)));
    } catch (FilePathException e) {
      System.out.println(MessageUtil.fileInputError());
    }
  }

  private static void combineUsersAndTasks() {
    for (Task task : tasksMap.values()) {
      usersMap.get(task.getUserId()).putTask(task);
    }
  }

  public static void cleanMemoryData() {
    if (usersMap.size() != 0 && tasksMap.size() != 0) {
      usersMap = new HashMap<>();
      tasksMap = new HashMap<>();
      System.out.println(MessageUtil.updateDataMessage());
    }
  }

  private static String getResourcePath(String filePath) {
    return Stream.of(filePath.split(" "))
            .map(String::valueOf)
            .collect(Collectors.joining(File.separator));
  }
}