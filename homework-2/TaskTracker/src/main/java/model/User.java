package model;

import java.util.ArrayList;
import java.util.List;

public class User {
  private int id;
  private String userName;
  private List<Task> taskList = new ArrayList<>();

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public List<Task> getTaskList() {
    return taskList;
  }

  public void setTaskList(List<Task> taskList) {
    this.taskList = taskList;
  }

  public void addTask(Task task) {
    taskList.add(task);
  }

  public User(int id, String userName) {
    this.id = id;
    this.userName = userName;
  }

  @Override
  public String toString() {
    return "User{" +
            "id:" + id +
            ", '" + userName + '\'' +
            ", taskList:" + taskList +
            '}';
  }
}
