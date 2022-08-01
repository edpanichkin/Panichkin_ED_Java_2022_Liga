package ru.edpanichkin.tasktracker.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_name")
    @NotEmpty
    private String username;
    @Column(name = "password")
    @NotEmpty
    private String password;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @Fetch(FetchMode.SUBSELECT)
    private List<Task> taskList;

    @Override
    public String toString() {
        return "<br/>User{" +
                "id:" + id +
                ", '" + username + '\'' +
                ", taskList: " + taskList.toString() +
                '}';
    }
}