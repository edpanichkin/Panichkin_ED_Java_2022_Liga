package ru.edpanichkin.tasktracker.service.reader;

import ru.edpanichkin.tasktracker.model.User;

public class UserCsvReader extends CsvReader<User> {

  private static final int USER_ID_POS = 0;
  private static final int USER_NAME_POS = 1;

  @Override
  protected User parseToObject(String[] line) {
    int userId = Integer.parseInt(line[USER_ID_POS].trim());
    String userName = line[USER_NAME_POS].trim();
    return new User(userId, userName);
  }

  @Override
  protected Integer getId(User obj) {
    return obj.getId();
  }
}