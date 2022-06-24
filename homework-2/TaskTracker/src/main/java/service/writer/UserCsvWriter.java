package service.writer;

import model.User;

public class UserCsvWriter extends CsvWriter<User>{
  @Override
  protected String objToStringForCsv(User obj) {
    return obj.getId() + CsvWriter.COMMA_DELIMITER + obj.getUserName() + System.lineSeparator();
  }
}
