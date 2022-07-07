package ru.edpanichkin.tasktracker.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.edpanichkin.tasktracker.model.EntityType;
import ru.edpanichkin.tasktracker.model.Task;
import ru.edpanichkin.tasktracker.model.User;
import ru.edpanichkin.tasktracker.repo.MemRepo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserEntityHandlerImpl implements EntityHandler<User> {
  private static final Map<Integer, User> usersMap = MemRepo.getMap(EntityType.USER);

  @Override
  public EntityType getType() {
    return EntityType.USER;
  }

  @Override
  public String view(String[] command) {
    log.error("USER view: " + Arrays.toString(command));
    User user = usersMap.get(Integer.parseInt(command[2]));
    return user == null ? "USER ID ERROR" : user.getId() + " " + user.getUserName() + " " +
            user.getTasksMapInUser()
                    .values()
                    .stream().sorted(Comparator.comparing(t -> t.getTaskStatus().ordinal()))
                    .collect(Collectors.toList());
  }

  @Override
  public String add(String[] command) {
    log.error("USER add: " + Arrays.toString(command));
    int id = MemRepo.getNextId(EntityType.USER);
    String userName = command[2];
    User user = new User(id, userName);
    usersMap.put(id, user);
    return "User added / id: " + id;
  }

  @Override
  public String delete(String[] command) {
    log.error("USER delete: " + Arrays.toString(command));
    int userId = Integer.parseInt(command[2]);
    User user = usersMap.get(userId);
    if (user == null) {
      return "USER ID ERROR";
    }
    return usersMap.remove(userId).toString();
  }

  @Override
  public String edit(String[] command) {
    log.error("USER edit: " + Arrays.toString(command));
    int userId = Integer.parseInt(command[2]);
    String newUserName = command[3];
    User user = usersMap.get(userId);
    if (user == null) {
      return "USER ID ERROR";
    }
    user.setUserName(newUserName);
    return "USERID " + userId + " name changed to " + newUserName;
  }
}
