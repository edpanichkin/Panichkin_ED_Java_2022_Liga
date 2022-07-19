package ru.edpanichkin.tasktracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.edpanichkin.tasktracker.service.TaskRepoService;

@Controller
public class TaskRepoController {

    TaskRepoService taskRepoService;

    @Autowired
    public TaskRepoController(TaskRepoService taskRepoService) {
        this.taskRepoService = taskRepoService;
    }

    @GetMapping("/task")
    public String getTask() {
        taskRepoService.init();
        return "OK";
    }
}