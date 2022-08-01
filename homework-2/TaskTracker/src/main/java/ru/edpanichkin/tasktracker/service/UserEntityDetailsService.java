package ru.edpanichkin.tasktracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.edpanichkin.tasktracker.model.User;
import ru.edpanichkin.tasktracker.repo.UserRepo;
import ru.edpanichkin.tasktracker.security.UserEntityDetails;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserEntityDetailsService implements UserDetailsService {

  private final UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepo.findByUsername(username);
    if(user.isEmpty()) {
      throw new UsernameNotFoundException("User not found!");
    }
    return new UserEntityDetails(user.get());
  }
}
