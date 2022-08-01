package ru.edpanichkin.tasktracker.util;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.edpanichkin.tasktracker.model.User;
import ru.edpanichkin.tasktracker.service.UserEntityDetailsService;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserValidator implements Validator {
  private final UserEntityDetailsService userEntityDetailsService;

  @Override
  public boolean supports(Class<?> clazz) {
    return User.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    User user = (User) target;
    try {
      userEntityDetailsService.loadUserByUsername(user.getUsername());
    }
    catch (UsernameNotFoundException ignored) {
      log.error(ignored.toString());
      return;
    }
    errors.rejectValue("username", "", "User with this username is exists");
  }
}
