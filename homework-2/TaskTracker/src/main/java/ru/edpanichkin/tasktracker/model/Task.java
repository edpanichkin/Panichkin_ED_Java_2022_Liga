package ru.edpanichkin.tasktracker.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "task")
@NoArgsConstructor
public class Task {
    @Id
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

    @Override
    public String toString() {
        return "\n <br/>   Task{" +
                "id:" + id +
                ", taskName='" + taskName + '\'' +
                ", taskInfo='" + taskInfo + '\'' +
                ", userId=" + userId +
                ", deadLine='" + date + '\'' +
                ", taskStatus=" + taskStatus +
                '}';
    }
}
