package ru.edpanichkin.tasktracker.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    @OneToMany(mappedBy = "user")
    @Fetch(FetchMode.SUBSELECT)
    private List<Task> taskList;

    @Override
    public String toString() {
        return "<br/>User{" +
                "id:" + id +
                ", '" + userName + '\'' +
                ", taskList: " + taskList.toString() +
                '}';
    }
}