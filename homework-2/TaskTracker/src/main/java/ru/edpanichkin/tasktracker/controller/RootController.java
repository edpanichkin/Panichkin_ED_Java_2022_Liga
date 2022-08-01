package ru.edpanichkin.tasktracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

  @GetMapping("/hello")
  public String helloPage() {
    return "/hello";
  }
}
