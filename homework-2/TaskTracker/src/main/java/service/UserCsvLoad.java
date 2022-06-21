package service;
import model.User;

public class UserCsvLoad extends CsvLoader<User>{

  @Override
  protected User addObject(String[] line) {
    return new User(Integer.parseInt(line[0].trim()), line[1].trim());
  }
}
