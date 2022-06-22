package model;

import java.time.LocalDate;

public class Task {

  private int id;
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

  public void setId(int id) {
    this.id = id;
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
    return "\n      Task{" +
            "id=" + id +
            ", taskName='" + taskName + '\'' +
            ", taskInfo='" + taskInfo + '\'' +
            ", userId=" + userId +
            ", deadLine='" + date + '\'' +
            ", taskStatus=" + taskStatus +
            '}';
  }
}
