package ru.edpanichkin.tasktracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edpanichkin.tasktracker.model.EntityType;
import ru.edpanichkin.tasktracker.model.Task;
import ru.edpanichkin.tasktracker.repo.MemRepo;
import ru.edpanichkin.tasktracker.repo.TaskRepo;

import javax.transaction.Transactional;
import java.util.Map;

@Service
public class TaskRepoService {
  private final TaskRepo taskRepo;

  @Autowired
  public TaskRepoService(TaskRepo taskRepo) {
    this.taskRepo = taskRepo;
  }
  @Transactional
  public void init() {
    Map<Integer, Task> taskMap = MemRepo.getEntityMap(EntityType.TASK);
    taskRepo.saveAll(taskMap.values());
  }

}
