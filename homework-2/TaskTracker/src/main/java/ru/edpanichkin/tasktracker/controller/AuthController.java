package ru.edpanichkin.tasktracker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.edpanichkin.tasktracker.model.User;
import ru.edpanichkin.tasktracker.service.RegistrationService;
import ru.edpanichkin.tasktracker.util.UserValidator;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {

  private final RegistrationService registrationService;
  private final UserValidator userValidator;

  @GetMapping("/login")
  public String loginPage() {
    return "auth/login";
  }

  @GetMapping("/registration")
  public String registrationPage(@ModelAttribute("user") User user) {
    return "auth/registration";
  }

  @PostMapping("/registration")
  public String performRegistration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
    userValidator.validate(user, bindingResult);
    if (bindingResult.hasErrors()) {
      log.error("HAS ERRORS / USERNAME");
      return "/auth/registration";
    }
    registrationService.register(user);
    return "redirect:/auth/login";
  }

}
