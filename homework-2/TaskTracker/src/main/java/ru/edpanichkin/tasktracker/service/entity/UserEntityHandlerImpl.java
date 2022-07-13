package ru.edpanichkin.tasktracker.service.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.edpanichkin.tasktracker.model.EntityType;
import ru.edpanichkin.tasktracker.model.User;
import ru.edpanichkin.tasktracker.repo.MemRepo;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserEntityHandlerImpl implements EntityHandler<User> {

  private static final int ADD_USER_NAME_POS = 2;
  private static final int USER_ID_POS = 2;
  private static final int EDIT_USER_NAME_POS = 3;

  private static final Map<Integer, User> usersMap = MemRepo.getEntityMap(EntityType.USER);

  @Override
  public EntityType getType() {
    return EntityType.USER;
  }

  @Override
  public String view(String[] command) {
    int userId = Integer.parseInt(command[USER_ID_POS]);
    User user = usersMap.get(userId);
    return user == null ? "USER ID ERROR" : user.getId() + " " + user.getUserName() + " " +
            user.getTasksMapInUser()
                    .values()
                    .stream().sorted(Comparator.comparing(t -> t.getTaskStatus().ordinal()))
                    .collect(Collectors.toList());
  }

  @Override
  public String add(String[] command) {
    int id = MemRepo.getNextId(EntityType.USER);
    String userName = command[ADD_USER_NAME_POS];
    User user = new User(id, userName);
    usersMap.put(id, user);
    return "User added / id: " + id;
  }

  @Override
  public String delete(String[] command) {
    int userId = Integer.parseInt(command[USER_ID_POS]);
    User user = usersMap.get(userId);
    if (user == null) {
      return "USER ID ERROR";
    }
    return usersMap.remove(userId).toString();
  }

  @Override
  public String edit(String[] command) {
    int userId = Integer.parseInt(command[USER_ID_POS]);
    String newUserName = command[EDIT_USER_NAME_POS];
    User user = usersMap.get(userId);
    if (user == null) {
      return "USER ID ERROR";
    }
    user.setUserName(newUserName);
    return "USERID " + userId + " name changed to " + newUserName;
  }

  @Override
  public Map<Integer, User> getMap() {
    return usersMap;
  }
}