package ru.edpanichkin.tasktracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.edpanichkin.tasktracker.repo.MemRepo;

@SpringBootApplication
public class TaskTrackerApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaskTrackerApplication.class, args);
    MemRepo.loadDataToProgram();
  }

}