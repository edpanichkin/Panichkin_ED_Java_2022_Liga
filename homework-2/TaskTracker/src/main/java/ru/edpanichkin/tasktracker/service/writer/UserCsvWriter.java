package ru.edpanichkin.tasktracker.service.writer;

import ru.edpanichkin.tasktracker.dto.UserFullDto;

public class UserCsvWriter extends CsvWriter<UserFullDto>{
  @Override
  protected String objToStringForCsv(UserFullDto user) {
    return user.getId() + COMMA_DELIMITER + user.getUserName() + System.lineSeparator();
  }
}

