package model;

import java.util.HashMap;
import java.util.Map;

public class User {
  private int id;
  private String userName;
  private Map<Integer,Task> tasksMapInUser = new HashMap<>();

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

  public Map<Integer, Task> getTasksMapInUser() {
    return tasksMapInUser;
  }

  public void setTasksMapInUser(Map<Integer, Task> tasksMapInUser) {
    this.tasksMapInUser = tasksMapInUser;
  }

  public void putTask(Task task) {
    tasksMapInUser.put(task.getId(), task);
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
            ", taskList:" + tasksMapInUser +
            '}';
  }
}
