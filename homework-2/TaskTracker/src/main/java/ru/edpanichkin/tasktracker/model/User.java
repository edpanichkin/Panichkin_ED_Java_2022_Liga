package ru.edpanichkin.tasktracker.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
  private
  @Id
  int id;
  private String userName;
  @OneToMany(mappedBy = "userId")
  private List<Task> taskList = new ArrayList<>();

  public void addTaskInRootList(Task task) {
    taskList.add(task);
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
            ", taskList: " + taskList.toString() +
            '}';
  }
}