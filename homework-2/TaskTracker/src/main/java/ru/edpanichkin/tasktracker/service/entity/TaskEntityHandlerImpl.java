package ru.edpanichkin.tasktracker.service.entity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.edpanichkin.tasktracker.dto.TaskFullDto;
import ru.edpanichkin.tasktracker.model.EntityType;
import ru.edpanichkin.tasktracker.model.Task;
import ru.edpanichkin.tasktracker.model.TaskStatus;
import ru.edpanichkin.tasktracker.service.TaskRepoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class TaskEntityHandlerImpl implements EntityHandler<Task> {

    private static final int TASK_ID_POS = 2;
    private static final int EDIT_TASK_STATUS_POS = 3;
    private static final int ADD_TASK_NAME_POS = 2;
    private static final int ADD_TASK_INFO_POS = 3;
    private static final int ADD_USER_ID_POS = 4;
    private static final int ADD_DATE_POS = 5;
    private static final int ADD_TASK_STATUS_POS = 6;
    private static final int ADD_TASK_WITH_STATUS = 7;

    private final TaskRepoService taskRepoService;

    @Override
    public EntityType getType() {
        return EntityType.TASK;
    }

    @Override
    public String view(String[] command) {
        int taskId = Integer.parseInt(command[TASK_ID_POS]);
        Task task = taskRepoService.getById(taskId);
        return task == null ? "TASK ID ERROR" : task.toString();
    }

    @Override
    @Transactional
    public String add(String[] command) {
        TaskFullDto taskDto = new TaskFullDto();
        taskDto.setTaskName(command[ADD_TASK_NAME_POS]);
        taskDto.setTaskInfo(command[ADD_TASK_INFO_POS]);
        taskDto.setUserId(Integer.parseInt(command[ADD_USER_ID_POS]));
        taskDto.setDate(LocalDate.parse(command[ADD_DATE_POS], DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        TaskStatus taskStatus = command.length == ADD_TASK_WITH_STATUS
                ? TaskStatus.values()[Integer.parseInt(command[ADD_TASK_STATUS_POS])]
                : TaskStatus.NEW;
        taskDto.setTaskStatus(taskStatus);
        return "Task added / id: " + taskRepoService.add(taskDto);
    }

    @Override
    public String delete(String[] command) {
        int taskId = Integer.parseInt(command[TASK_ID_POS]);
        taskRepoService.delete(taskId);
        return "DELETED TASK " + taskId;
    }

    @Override
    public String edit(String[] command) {
            int taskId = Integer.parseInt(command[TASK_ID_POS]);
    TaskStatus taskStatus = TaskStatus.values()[Integer.parseInt(command[EDIT_TASK_STATUS_POS])];
    String result = taskRepoService.edit(taskId, taskStatus);
    return Objects.equals(result, "UPDATE") ? "TASK STATUS " + taskId + " changed to " + taskStatus : "ERROR";
    }


//  @Override
//  public String add(String[] command) {
//    int id = MemRepo.getNextId(EntityType.TASK);
//    String taskName = command[ADD_TASK_NAME_POS];
//    String taskInfo = command[ADD_TASK_INFO_POS];
//    int userId = Integer.parseInt(command[ADD_USER_ID_POS]);
//    LocalDate date = LocalDate.parse(command[ADD_DATE_POS], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
//    Task task = new Task(id, taskName, taskInfo, userId, date, TaskStatus.NEW);
//    tasksMap.put(id, task);
//    return "Task added / id: " + id;
//  }

//  @Override
//  public String delete(String[] command) {
//    int taskId = Integer.parseInt(command[TASK_ID_POS]);
//    int userId = getUserIdOwner(taskId);
//    if (userId == -1) {
//      return "ERROR ID";
//    }
//    Map<Integer, User> usersMap = MemRepo.getEntityMap(EntityType.USER);
//    int taskIndexInUser = usersMap.get(userId).getTaskList().indexOf(taskId);
//    usersMap.get(userId).getTaskList().remove(tasksMap.get(taskId));
//    tasksMap.remove(taskId);
//    return "DELETED TASK " + taskId + " and in userId " + userId;
//  }
//
//  @Override
//  public String edit(String[] command) {
//    int taskId = Integer.parseInt(command[TASK_ID_POS]);
//    TaskStatus taskStatus = TaskStatus.values()[Integer.parseInt(command[EDIT_TASK_STATUS_POS])];
//    tasksMap.get(taskId).setTaskStatus(taskStatus);
//    return "TASK STATUS " + taskId + " changed to " + taskStatus;

}