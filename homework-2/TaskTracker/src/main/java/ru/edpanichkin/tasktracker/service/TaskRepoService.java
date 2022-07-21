package ru.edpanichkin.tasktracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.edpanichkin.tasktracker.dto.TaskFullDto;
import ru.edpanichkin.tasktracker.model.Task;
import ru.edpanichkin.tasktracker.model.TaskStatus;
import ru.edpanichkin.tasktracker.model.User;
import ru.edpanichkin.tasktracker.repo.TaskRepo;


@Service
@RequiredArgsConstructor
public class TaskRepoService {
    private final TaskRepo taskRepo;
    private final UserRepoService userRepoService;

    public Task getById(Integer id) {
        return taskRepo.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        taskRepo.delete(getById(id));
    }

    @Transactional
    public int add(TaskFullDto taskDto) {
        Task task = new Task();
        task.setTaskName(taskDto.getTaskName());
        task.setTaskInfo(taskDto.getTaskInfo());
        task.setTaskStatus(taskDto.getTaskStatus());
        task.setDate(taskDto.getDate());
        User user = userRepoService.getById(taskDto.getUserId());
        task.setUser(user);
        return taskRepo.save(task).getId();
    }

    @Transactional
    public String edit(int taskId, TaskStatus taskStatus) {
        Task task = getById(taskId);
        if (task == null) {
            return "ERROR TASK ID";
        }
        task.setTaskStatus(taskStatus);
        taskRepo.save(task);
        return "UPDATE";
    }
}
