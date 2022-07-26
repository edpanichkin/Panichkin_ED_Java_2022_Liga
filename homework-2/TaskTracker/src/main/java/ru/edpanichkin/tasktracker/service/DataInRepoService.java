package ru.edpanichkin.tasktracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edpanichkin.tasktracker.model.User;
import ru.edpanichkin.tasktracker.repo.MemRepo;
import ru.edpanichkin.tasktracker.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataInRepoService {

  private final UserRepoService userRepoService;
  private final TaskRepoService taskRepoService;
  private final MemRepo memRepo;

  public String clearState() {
    taskRepoService.clearState();
    userRepoService.clearState();
    return MessageUtil.cleanedMemoryMessage();
  }

  public List<String> viewAll() {
    return userRepoService.findAll().stream().map(User::toString).collect(Collectors.toList());
  }

  public String saveState() {
    memRepo.saveState();
    return MessageUtil.saveDataMessage();
  }
}
