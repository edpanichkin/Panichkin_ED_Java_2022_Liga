package ru.edpanichkin.tasktracker.service.writer;

import ru.edpanichkin.tasktracker.model.User;

public class UserCsvWriter extends CsvWriter<User>{
  @Override
  protected String objToStringForCsv(User obj) {
    return obj.getId() + COMMA_DELIMITER + obj.getUserName() + System.lineSeparator();
  }
}
