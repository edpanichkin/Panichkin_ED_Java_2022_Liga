package ru.edpanichkin.tasktracker.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "task_info")
    private String taskInfo;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "task_status")
    private TaskStatus taskStatus;

    @Override
    public String toString() {
        return "<br/>Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", taskInfo='" + taskInfo + '\'' +
                ", userId=" + user.getId() +
                ", date=" + date +
                ", taskStatus=" + taskStatus +
                '}';
    }
}