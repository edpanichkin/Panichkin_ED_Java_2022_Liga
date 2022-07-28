package ru.edpanichkin.tasktracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.edpanichkin.tasktracker.service.CommandHandler;
import ru.edpanichkin.tasktracker.service.UserRepoService;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class CommandController {
  private final UserRepoService userRepoService;

  @GetMapping("/api/v1/cl/{command}")
  @ResponseBody
  public String getCommand(@PathVariable String command) {
    return CommandHandler.parseCommand(command);
  }

  @GetMapping("/api/v1/busy")
  @ResponseBody
  public Object getUserWithMaxTasks(@RequestParam(name = "type", required = false) String type,
                                    @RequestParam(name = "min_date", required = false) LocalDate minDate,
                                    @RequestParam(name = "max_date", required = false) LocalDate maxDate) {
    return userRepoService.findUserWithMaxQuantityOfTasks(type, minDate, maxDate);
  }
}