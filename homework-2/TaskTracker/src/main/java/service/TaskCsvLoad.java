package service;

import model.Task;
import model.TaskStatus;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskCsvLoad extends CsvLoader<Task> {

  @Override
  protected Task addObject(String[] line) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    return new Task(Integer.parseInt(line[0]), line[1].trim(), line[2].trim(),
            Integer.parseInt(line[3]), LocalDate.parse(line[4].trim(), formatter), TaskStatus.NEW);
  }
}
