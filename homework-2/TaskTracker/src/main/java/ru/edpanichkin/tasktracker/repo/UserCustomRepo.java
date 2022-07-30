package ru.edpanichkin.tasktracker.repo;

import org.springframework.data.jpa.domain.Specification;
import ru.edpanichkin.tasktracker.dto.UserWithTasksDto;
import ru.edpanichkin.tasktracker.model.Task;

public interface UserCustomRepo {
  UserWithTasksDto findUserWithMaxQuantityOfTasks(Specification<Task> specification);
}
