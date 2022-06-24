package service.reader;

import model.User;

public class UserCsvReader extends CsvReader<User> {

  @Override
  protected User parseToObject(String[] line) {
    return new User(Integer.parseInt(line[0].trim()), line[1].trim());
  }

  @Override
  protected Integer getId(User obj) {
    return obj.getId();
  }
}
