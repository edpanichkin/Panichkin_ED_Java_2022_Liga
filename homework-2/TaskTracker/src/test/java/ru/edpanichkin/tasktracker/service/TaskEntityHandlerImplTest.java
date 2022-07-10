package ru.edpanichkin.tasktracker.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import ru.edpanichkin.tasktracker.model.EntityType;
import ru.edpanichkin.tasktracker.model.Task;
import ru.edpanichkin.tasktracker.repo.MemRepo;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class TaskEntityHandlerImplTest {
  Map<Integer, Task> taskMap = new HashMap<>();

  @BeforeEach
  public void setUp() {
    MemRepo.loadDataToProgram();
    taskMap = MemRepo.getMap(EntityType.TASK);
  }

  @Test
  void getType() {

  }

  @Test
  void view_Task_by_Id_String_true() {
    String viewTask1 = CommandHandler.parseCommand("view task 1");
    String viewTask2 = CommandHandler.parseCommand("view task 2");
    String viewTask3 = CommandHandler.parseCommand("view task 3");
    String viewTask4 = CommandHandler.parseCommand("view task 4");

    Assertions.assertEquals(taskMap.get(1).toString(), viewTask1);
    Assertions.assertEquals(taskMap.get(2).toString(), viewTask2);
    Assertions.assertEquals(taskMap.get(3).toString(), viewTask3);
    Assertions.assertEquals(taskMap.get(4).toString(), viewTask4);
  }

  @Test
  void add() {
  }

  @Test
  void delete() {
  }

  @Test
  void edit() {
  }

  @Test
  void getId() {
  }
}