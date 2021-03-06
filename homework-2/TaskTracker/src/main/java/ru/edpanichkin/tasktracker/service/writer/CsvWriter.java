package ru.edpanichkin.tasktracker.service.writer;

import ru.edpanichkin.tasktracker.exception.FilePathException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

abstract public class CsvWriter<T> {
  static final String COMMA_DELIMITER = ",";

  protected abstract String objToStringForCsv(T obj);


  public void writeToCsv(Map<Integer, T> map, Path path) throws FilePathException {
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

