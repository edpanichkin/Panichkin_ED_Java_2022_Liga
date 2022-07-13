package ru.edpanichkin.tasktracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edpanichkin.tasktracker.model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {

}
