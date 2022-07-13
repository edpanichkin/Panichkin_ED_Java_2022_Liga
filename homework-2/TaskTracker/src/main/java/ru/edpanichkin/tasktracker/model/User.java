package ru.edpanichkin.tasktracker.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User {
  private
  @Id
  int id;
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
    return "<br/>User{" +
            "id:" + id +
            ", '" + userName + '\'' +
            ", taskList: " + tasksMapInUser.values() +
            '}';
  }
}