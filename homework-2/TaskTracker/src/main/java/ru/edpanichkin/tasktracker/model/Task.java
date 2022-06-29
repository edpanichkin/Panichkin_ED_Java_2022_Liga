package ru.edpanichkin.tasktracker.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
public class Task {

  private final int id;
  private String taskName;
  private String taskInfo;
  private int userId;
  private LocalDate date;
  private TaskStatus taskStatus;

  public Task(int id, String taskName, String taskInfo, int userId, LocalDate date, TaskStatus taskStatus) {
    this.id = id;
    this.taskName = taskName;
    this.taskInfo = taskInfo;
    this.userId = userId;
    this.date = date;
    this.taskStatus = taskStatus;
  }

  @Override
  public String toString() {
    return "\n   Task{" +
            "id= " + id +
            ", taskName='" + taskName + '\'' +
            ", taskInfo='" + taskInfo + '\'' +
            ", userId=" + userId +
            ", deadLine='" + date + '\'' +
            ", taskStatus=" + taskStatus +
            '}';
  }
//
//  @Override
//  public boolean equals(Object o) {
//    if (this == o) return true;
//    if (o == null || getClass() != o.getClass()) return false;
//    Task task = (Task) o;
//    return id == task.id && userId == task.userId
//            && taskName.equals(task.taskName) && taskInfo.equals(task.taskInfo)
//            && date.equals(task.date) && taskStatus == task.taskStatus;
//  }
//
//  @Override
//  public int hashCode() {
//    return Objects.hash(id, taskName, taskInfo, userId, date, taskStatus);
//  }
}
