package ru.edpanichkin.tasktracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edpanichkin.tasktracker.model.User;
import ru.edpanichkin.tasktracker.repo.UserRepo;

@Service
@RequiredArgsConstructor
public class UserRepoService {
  private final UserRepo userRepo;

  public User getById(Integer id) {
    return userRepo.findById(id).orElse(null);
  }

  public void delete(Integer id){
    userRepo.delete(getById(id));
  }

}
