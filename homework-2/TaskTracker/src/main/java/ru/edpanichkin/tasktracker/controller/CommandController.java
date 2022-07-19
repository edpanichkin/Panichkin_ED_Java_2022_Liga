package ru.edpanichkin.tasktracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.edpanichkin.tasktracker.service.CommandHandler;

@Controller
public class CommandController {



  @GetMapping("/{command}")
  @ResponseBody
  public String getCommand(@PathVariable String command) {
    return CommandHandler.parseCommand(command);
  }

//
//  @GetMapping("/sort/{command}")
//  @ResponseBody
//  public String getSortWithParam(@PathVariable String command) {
//    return CommandHandler.parseCommand(command);
//  }
}

//  Specification и hibernate-jpamodelgen