package ru.edpanichkin.tasktracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.edpanichkin.tasktracker.dto.UserWithTasksDto;
import ru.edpanichkin.tasktracker.model.Task;
import ru.edpanichkin.tasktracker.model.User;
import ru.edpanichkin.tasktracker.repo.UserRepo;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRepoService {
  private final UserRepo userRepo;

  public User getById(Integer id) {
    return userRepo.findById(id).orElse(null);
  }

  public void delete(Integer id) {
    userRepo.delete(getById(id));
  }

  public void clearState() {
    userRepo.deleteAll();
  }

  @Transactional
  public int add(User user) {
    return userRepo.save(user).getId();
  }

  @Transactional
  public String edit(int userId, String newUsername) {
    User user = getById(userId);
    if (user == null) {
      return "ERROR USER ID";
    }
    user.setUsername(newUsername);
    userRepo.save(user);
    return "UPDATE";
  }

  public List<User> findAll() {
    return userRepo.findAll();
  }

  public UserWithTasksDto findUserWithMaxQuantityOfTasks(String type, LocalDate minDate, LocalDate maxDate) {
    Specification<Task> tasksSpec = TasksSpecificationProvider.getTasksSpecification(type, minDate, maxDate);
    return userRepo.findUserWithMaxQuantityOfTasks(tasksSpec);
  }
}
