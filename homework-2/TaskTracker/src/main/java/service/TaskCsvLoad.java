package service;

import model.Task;
import model.TaskStatus;


public class TaskCsvLoad extends CsvLoader<Task> {

  @Override
  protected Task addObject(String[] line) {
    return new Task(Integer.parseInt(line[0]), line[1].trim(), line[2].trim(),
            Integer.parseInt(line[3]), line[4].trim(), TaskStatus.NEW);
  }
}
