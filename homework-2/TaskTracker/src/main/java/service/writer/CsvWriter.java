package service.writer;

import exception.FilePathException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

abstract public class CsvWriter<T> {
  static final String COMMA_DELIMITER = ",";

  protected abstract String objToStringForCsv(T obj);


  public void writeToCsv(Map<Integer, T> map, Path path) throws FilePathException {
//    Path path = Paths.get("src","main", "resources", "writeTask.csv");
    System.out.println(path);
    try {
      Files.writeString(path, "");
      for (Integer key : map.keySet()) {
        Files.writeString(path, objToStringForCsv(map.get(key)), StandardOpenOption.APPEND);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}

