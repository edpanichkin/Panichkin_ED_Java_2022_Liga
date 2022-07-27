package ru.edpanichkin.tasktracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.edpanichkin.tasktracker.model.TaskStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskFullDto {

  private int id;
  private String taskName;
  private String taskInfo;
  private int userId;
  private LocalDate date;
  private TaskStatus taskStatus;

}