package service.reader;

import model.Task;
import model.TaskStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskCsvReader extends CsvReader<Task> {
  private static final int MAX_CSV_VALUES = 6;

  @Override
  protected Task parseToObject(String[] line) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    return new Task(
            Integer.parseInt(line[0]),
            line[1].trim(),
            line[2].trim(),
            Integer.parseInt(line[3]),
            LocalDate.parse(line[4].trim(), formatter),
            line.length == MAX_CSV_VALUES ? TaskStatus.values()[Integer.parseInt(line[5])] : TaskStatus.NEW);
  }

  @Override
  protected Integer getId(Task obj) {
    return obj.getId();
  }
}
