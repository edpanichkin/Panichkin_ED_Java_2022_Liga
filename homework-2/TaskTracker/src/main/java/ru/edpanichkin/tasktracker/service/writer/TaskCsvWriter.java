package ru.edpanichkin.tasktracker.service.writer;
import ru.edpanichkin.tasktracker.model.Task;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskCsvWriter extends CsvWriter<Task> {
  @Override
  protected String objToStringForCsv(Task task) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    return Stream.of(task.getId(),
                    task.getTaskName(),
                    task.getTaskInfo(),
                    task.getUserId(),
                    task.getDate().format(formatter),
                    task.getTaskStatus().ordinal())
            .map(String::valueOf)
            .collect(Collectors.joining(COMMA_DELIMITER)) + System.lineSeparator();
  }
}
