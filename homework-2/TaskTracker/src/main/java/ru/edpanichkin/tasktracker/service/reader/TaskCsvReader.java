package ru.edpanichkin.tasktracker.service.reader;

import ru.edpanichkin.tasktracker.model.Task;
import ru.edpanichkin.tasktracker.model.TaskStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskCsvReader extends CsvReader<Task> {

  private static final int TASK_ID_POS = 0;
  private static final int TASK_NAME_POS = 1;
  private static final int TASK_INFO_POS = 2;
  private static final int USER_ID_POS = 3;
  private static final int DATE_POS = 4;
  private static final int TASK_STATUS_POS = 5;
  private static final int MAX_CSV_VALUES = 6;

  @Override
  protected Task parseToObject(String[] line) {
    int taskId = Integer.parseInt(line[TASK_ID_POS]);
    String taskName = line[TASK_NAME_POS].trim();
    String taskInfo = line[TASK_INFO_POS].trim();
    int userId = Integer.parseInt(line[USER_ID_POS]);
    LocalDate date = LocalDate.parse(line[DATE_POS].trim(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    TaskStatus taskStatus = line.length == MAX_CSV_VALUES ?
            TaskStatus.values()[Integer.parseInt(line[TASK_STATUS_POS])] : TaskStatus.NEW;
    return new Task(taskId, taskName, taskInfo, userId, date, taskStatus);
  }

  @Override
  protected Integer getId(Task obj) {
    return obj.getId();
  }
}