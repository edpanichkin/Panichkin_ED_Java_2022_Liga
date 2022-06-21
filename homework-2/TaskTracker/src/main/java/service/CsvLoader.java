package service;

import exception.FilePathException;
import model.Task;
import model.TaskStatus;
import model.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

abstract public class CsvLoader<T> {
  static final  String COMMA_DELIMITER = ",";

  protected abstract T addObject(String[] line);

  public List<T> loadFromCsv(String fileName) throws FilePathException{
    List<String> stringLinesFromFile = new ArrayList<>();
    List<T> list = new ArrayList<>();
    try {
      stringLinesFromFile = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
      for (String line : stringLinesFromFile) {
        list.add(addObject(line.split(COMMA_DELIMITER)));
      }
    } catch (IOException e) {
      //System.out.println("IOException / wrong filepath: " + fileName + " ");
      throw new FilePathException("IOException / wrong filepath: " + fileName);
    }
    return list;
  }

}

