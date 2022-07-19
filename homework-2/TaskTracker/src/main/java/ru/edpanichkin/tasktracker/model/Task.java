package ru.edpanichkin.tasktracker.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor

public class Task {
    @Id
    private int id;
    private String taskName;
    private String taskInfo;
    private int userId;
    private LocalDate date;
    private TaskStatus taskStatus;

    @Override
    public String toString() {
        return "<br/>Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", taskInfo='" + taskInfo + '\'' +
                ", userId=" + userId +
                ", date=" + date +
                ", taskStatus=" + taskStatus +
                '}';
    }
}