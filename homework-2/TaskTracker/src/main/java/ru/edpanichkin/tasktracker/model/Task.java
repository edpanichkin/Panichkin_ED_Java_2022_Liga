package ru.edpanichkin.tasktracker.model;

import java.time.LocalDate;
import java.util.Objects;

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

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public int getId() {
    return id;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getTaskInfo() {
    return taskInfo;
  }

  public void setTaskInfo(String taskInfo) {
    this.taskInfo = taskInfo;
  }

  public TaskStatus getTaskStatus() {
    return taskStatus;
  }

  public void setTaskStatus(TaskStatus taskStatus) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Task task = (Task) o;
    return id == task.id && userId == task.userId
            && taskName.equals(task.taskName) && taskInfo.equals(task.taskInfo)
            && date.equals(task.date) && taskStatus == task.taskStatus;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, taskName, taskInfo, userId, date, taskStatus);
  }
}
