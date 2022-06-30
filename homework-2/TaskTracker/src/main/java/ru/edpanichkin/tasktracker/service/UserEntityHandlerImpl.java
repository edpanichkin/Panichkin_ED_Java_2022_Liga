package ru.edpanichkin.tasktracker.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.edpanichkin.tasktracker.model.EntityType;
import ru.edpanichkin.tasktracker.model.User;
import ru.edpanichkin.tasktracker.repo.MemRepo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserEntityHandlerImpl implements EntityHandler<User> {
  @Override
  public EntityType getType() {
    return EntityType.USER;
  }

  @Override
  public String view(String[] command) {
    log.error("USER view: " + Arrays.toString(command));
    User user = MemRepo.usersMap.get(Integer.parseInt(command[2]));
    return user.getId() + " " + user.getUserName() + " " +
            user.getTasksMapInUser()
                    .values()
                    .stream().sorted(Comparator.comparing(t -> t.getTaskStatus().ordinal()))
                    .collect(Collectors.toList()).toString();
  }

  @Override
  public String add(String[] command) {
    log.error("USER add: " + Arrays.toString(command));
    int id = 0;
    Optional<Integer> maxId = MemRepo.usersMap.keySet().stream().max(Integer::compare);
    if (maxId.isPresent()) {
      id = maxId.get() + 1;
      log.error("MAXID " + maxId.get());
    }
    // 0    1     2
    //add user userName
    //add user Spring
    User user = new User(id, command[2]);
    MemRepo.usersMap.put(id, user);
    return "User added / id: " + id;
  }

  @Override
  public String delete(String[] command) {
    log.error("USER delete: " + Arrays.toString(command));
    return MemRepo.usersMap.remove(Integer.parseInt(command[2])).toString();
  }

  @Override
  public String edit(String[] command) {
    log.error("USER edit: " + Arrays.toString(command));
    int userId = Integer.parseInt(command[2]);
    MemRepo.usersMap.get(userId).setUserName(command[3]);
    return "USERID " + userId + " name changed to " + command[3];
  }
}
