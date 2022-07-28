package ru.edpanichkin.tasktracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithTasksDto {

  private int id;
  private String userName;
  private List<TaskFullDto> tasks;
}
