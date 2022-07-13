package ru.edpanichkin.tasktracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.edpanichkin.tasktracker.service.UserRepoService;

@Controller
public class UserRepoController {

    UserRepoService userRepoService;

    @Autowired
    public UserRepoController(UserRepoService userRepoService) {
        this.userRepoService = userRepoService;
    }

    @GetMapping("/user")
    public String getUser() {
        userRepoService.init();
        return "OK";
    }
}
