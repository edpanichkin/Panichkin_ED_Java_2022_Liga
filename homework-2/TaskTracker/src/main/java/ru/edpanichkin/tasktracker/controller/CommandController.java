package ru.edpanichkin.tasktracker.controller;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.edpanichkin.tasktracker.dto.UserWithTasksDto;
import ru.edpanichkin.tasktracker.model.User;
import ru.edpanichkin.tasktracker.security.UserEntityDetails;
import ru.edpanichkin.tasktracker.service.CommandHandler;
import ru.edpanichkin.tasktracker.service.UserRepoService;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommandController {
  private final UserRepoService userRepoService;

  @GetMapping("/cl/{command}")
  @ResponseBody
  public String getCommand(@PathVariable String command) {
    return CommandHandler.parseCommand(command);
  }

  @GetMapping("/busy")
  @ResponseBody
  public UserWithTasksDto getUserWithMaxTasks(@RequestParam(name = "type", required = false) String type,
                                              @RequestParam(name = "min_date", required = false) LocalDate minDate,
                                              @RequestParam(name = "max_date", required = false) LocalDate maxDate) {
    return userRepoService.findUserWithMaxQuantityOfTasks(type, minDate, maxDate);
  }

  @GetMapping("/security")
  public Object showUserInfo() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserEntityDetails userEntityDetails = (UserEntityDetails) authentication.getPrincipal();
    System.out.println(userEntityDetails.getUser().toString());
    return userEntityDetails.getUser();
  }
}