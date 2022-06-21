package model;

public enum TaskStatus {

  NEW("новое"),
  IN_WORK("в работе"),
  DONE("готово");
  private final String status;

  TaskStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
