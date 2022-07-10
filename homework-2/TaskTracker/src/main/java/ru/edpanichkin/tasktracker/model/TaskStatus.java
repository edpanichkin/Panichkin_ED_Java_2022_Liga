package ru.edpanichkin.tasktracker.model;

public enum TaskStatus {

  NEW("Новая"),
  IN_WORK("В работе"),
  DONE("Готово");
  private final String status;

  TaskStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
