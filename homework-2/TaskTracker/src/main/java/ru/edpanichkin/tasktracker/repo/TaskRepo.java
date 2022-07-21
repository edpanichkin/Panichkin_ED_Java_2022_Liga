package ru.edpanichkin.tasktracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edpanichkin.tasktracker.model.Task;

import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {
    @Override
    Optional<Task> findById(Integer integer);
}
