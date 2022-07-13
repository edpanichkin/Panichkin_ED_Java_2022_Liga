package ru.edpanichkin.tasktracker.service;

import org.junit.jupiter.api.*;

import org.springframework.boot.test.context.SpringBootTest;
import ru.edpanichkin.tasktracker.model.EntityType;
import ru.edpanichkin.tasktracker.model.Task;
import ru.edpanichkin.tasktracker.model.TaskStatus;
import ru.edpanichkin.tasktracker.repo.MemRepo;
import ru.edpanichkin.tasktracker.service.entity.EntityFactory;
import ru.edpanichkin.tasktracker.service.entity.TaskEntityHandlerImpl;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@SpringBootTest
class TaskEntityHandlerImplTest {
  TaskEntityHandlerImpl taskEntityHandler = new TaskEntityHandlerImpl();
  Map<Integer, Task> taskMap = taskEntityHandler.getMap();

  @BeforeEach
  void init() {
    MemRepo.loadDataToProgram();
  }

  @Test
  @DisplayName("view task {id} / get true")
  void view_Task_by_Id_String_true() {

    String viewTask1 = CommandHandler.parseCommand("view task 1");
    String viewTask2 = CommandHandler.parseCommand("vIeW tAsK    2");
    String viewTask3 = CommandHandler.parseCommand("view    task  3");
    String viewTask4 = CommandHandler.parseCommand("view task 4 ");

    Assertions.assertEquals(taskMap.get(1).toString(), viewTask1);
    Assertions.assertEquals(taskMap.get(2).toString(), viewTask2);
    Assertions.assertEquals(taskMap.get(3).toString(), viewTask3);
    Assertions.assertEquals(taskMap.get(4).toString(), viewTask4);
    System.out.println(taskMap.toString());
  }

  @Test
  void add_newTask() {
    int newTaskId = MemRepo.getNextId(EntityType.TASK);
    CommandHandler.parseCommand("add task Spring Внесена_из_теста 3 29.07.2022");
    Assertions.assertEquals(newTaskId, taskMap.get(newTaskId).getId());
    Assertions.assertEquals("Spring", taskMap.get(newTaskId).getTaskName());
    Assertions.assertEquals("Внесена_из_теста", taskMap.get(newTaskId).getTaskInfo());
    Assertions.assertEquals(3, taskMap.get(newTaskId).getUserId());
    Assertions.assertEquals(LocalDate.parse("29.07.2022", DateTimeFormatter.ofPattern("dd.MM.yyyy")),
            taskMap.get(newTaskId).getDate());
    Assertions.assertEquals(TaskStatus.NEW, taskMap.get(newTaskId).getTaskStatus());
  }

  @Test
  void delete_task_return_true() {
    int taskMapSize = taskMap.size();
    CommandHandler.parseCommand("deLeTe   TasK   8");
    Assertions.assertEquals(--taskMapSize, taskMap.size());
    Assertions.assertNull(taskMap.get(8));
  }

  @Test
  @DisplayName("edit task status / get true")
  void edit_Task_return_True() {
    CommandHandler.parseCommand("edit task 1 2");
    CommandHandler.parseCommand("EdIT   tAsK    2   1");
    Assertions.assertEquals(TaskStatus.DONE, taskMap.get(1).getTaskStatus());
    Assertions.assertEquals(TaskStatus.IN_WORK, taskMap.get(2).getTaskStatus());
    CommandHandler.parseCommand("EdIT   tAsK    2     0");
    Assertions.assertEquals(TaskStatus.NEW, taskMap.get(2).getTaskStatus());
  }

  @Test
  void getId_task_when_find() {

    Assertions.assertEquals(taskMap.get(1).getUserId(), taskEntityHandler.getUserIdOwner(1));
    Assertions.assertEquals(taskMap.get(2).getUserId(), taskEntityHandler.getUserIdOwner(2));
    Assertions.assertEquals(-1, taskEntityHandler.getUserIdOwner(100));
  }

  @Test
  void getType_Return_True() {
    Assertions.assertEquals(EntityType.TASK, EntityFactory.getCommander(EntityType.TASK).getType());
  }
}