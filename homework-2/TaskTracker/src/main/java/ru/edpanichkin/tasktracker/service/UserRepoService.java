package ru.edpanichkin.tasktracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edpanichkin.tasktracker.model.EntityType;
import ru.edpanichkin.tasktracker.model.Task;
import ru.edpanichkin.tasktracker.model.User;
import ru.edpanichkin.tasktracker.repo.MemRepo;
import ru.edpanichkin.tasktracker.repo.TaskRepo;
import ru.edpanichkin.tasktracker.repo.UserRepo;

import javax.transaction.Transactional;
import java.util.Map;

@Service
public class UserRepoService {
  private final UserRepo userRepo;

  @Autowired
  public UserRepoService(UserRepo userRepo) {
    this.userRepo = userRepo;
  }
  @Transactional
  public void init() {
    Map<Integer, User> userMap = MemRepo.getEntityMap(EntityType.USER);
    userRepo.saveAll(userMap.values());
  }

}
