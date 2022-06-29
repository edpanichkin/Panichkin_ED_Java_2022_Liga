package ru.edpanichkin.tasktracker.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode
public class User {
  private final int id;
  private String userName;
  private Map<Integer, Task> tasksMapInUser = new HashMap<>();

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
}