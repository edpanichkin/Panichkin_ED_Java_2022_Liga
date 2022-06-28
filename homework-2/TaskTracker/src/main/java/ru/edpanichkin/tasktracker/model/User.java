package ru.edpanichkin.tasktracker.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
  private final int id;
  private String userName;
  private Map<Integer, Task> tasksMapInUser = new HashMap<>();

  public int getId() {
    return id;
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
            ", taskList: " + tasksMapInUser.values() +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return id == user.id && userName.equals(user.userName) && Objects.equals(tasksMapInUser, user.tasksMapInUser);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userName, tasksMapInUser);
  }
}