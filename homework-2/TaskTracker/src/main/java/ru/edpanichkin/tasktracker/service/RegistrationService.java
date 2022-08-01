package ru.edpanichkin.tasktracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.edpanichkin.tasktracker.model.User;
import ru.edpanichkin.tasktracker.repo.UserRepo;

@Service
@RequiredArgsConstructor
public class RegistrationService {
  private final UserRepo userRepo;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public void register(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepo.save(user);
  }
}
