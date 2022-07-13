package ru.edpanichkin.tasktracker.service.reader;

import ru.edpanichkin.tasktracker.model.User;

public class UserCsvReader extends CsvReader<User> {

  @Override
  protected User parseToObject(String[] line) {
    int userId = Integer.parseInt(line[0].trim());
    String userName = line[1].trim();
    return new User(userId, userName);
  }

  @Override
  protected Integer getId(User obj) {
    return obj.getId();
  }
}
