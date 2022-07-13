package ru.edpanichkin.tasktracker.service.reader;

import ru.edpanichkin.tasktracker.model.Task;
import ru.edpanichkin.tasktracker.model.TaskStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskCsvReader extends CsvReader<Task> {
  private static final int MAX_CSV_VALUES = 6;

  @Override
  protected Task parseToObject(String[] line) {
    int taskId = Integer.parseInt(line[0]);
    String taskName = line[1].trim();
    String taskInfo = line[2].trim();
    int userId = Integer.parseInt(line[3]);
    LocalDate date = LocalDate.parse(line[4].trim(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    TaskStatus taskStatus = line.length == MAX_CSV_VALUES ?
            TaskStatus.values()[Integer.parseInt(line[5])] : TaskStatus.NEW;
    return new Task(taskId, taskName, taskInfo, userId, date, taskStatus);
  }

  @Override
  protected Integer getId(Task obj) {
    return obj.getId();
  }
}
