package ru.edpanichkin.tasktracker.service.entity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.edpanichkin.tasktracker.model.EntityType;
import ru.edpanichkin.tasktracker.model.User;
import ru.edpanichkin.tasktracker.service.UserRepoService;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserEntityTypeHandlerImpl implements EntityTypeHandler<User> {

  private static final int ADD_USER_NAME_POS = 2;
  private static final int USER_ID_POS = 2;
  private static final int EDIT_USER_NAME_POS = 3;

  private final UserRepoService userRepoService;

  @Override
  public EntityType getType() {
    return EntityType.USER;
  }

  @Override
  public String view(String[] command) {
    int userId = Integer.parseInt(command[USER_ID_POS]);
    User user = userRepoService.getById(userId);
    return user == null ? "USER ID ERROR" : user.getId() + " " + user.getUsername() + " " +
            user.getTaskList()
                    .stream().sorted(Comparator.comparing(t -> t.getTaskStatus().ordinal()))
                    .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public String add(String[] command) {
    User user = new User();
    user.setUsername(command[ADD_USER_NAME_POS]);
    return "User added / id: " + userRepoService.add(user);
  }

  @Override
  public String delete(String[] command) {
    int userId = Integer.parseInt(command[USER_ID_POS]);
    userRepoService.delete(userId);
    return "DELETED USER " + userId;
  }

  @Override
  public String edit(String[] command) {
    int userId = Integer.parseInt(command[USER_ID_POS]);
    String newUserName = command[EDIT_USER_NAME_POS];
    String result = userRepoService.edit(userId, newUserName);
    return Objects.equals(result, "UPDATE") ? "USER NAME " + userId + " changed to " + newUserName : "ERROR";

  }
}