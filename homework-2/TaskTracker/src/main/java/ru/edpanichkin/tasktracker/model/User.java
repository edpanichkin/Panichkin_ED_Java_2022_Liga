package ru.edpanichkin.tasktracker.model;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    private
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
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