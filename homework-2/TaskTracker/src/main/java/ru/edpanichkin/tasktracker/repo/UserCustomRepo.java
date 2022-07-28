package ru.edpanichkin.tasktracker.repo;

import org.springframework.data.jpa.domain.Specification;
import ru.edpanichkin.tasktracker.model.Task;

public interface UserCustomRepo {
  Object findUserWithMaxQuantityOfTasks(Specification<Task> specification);
}
