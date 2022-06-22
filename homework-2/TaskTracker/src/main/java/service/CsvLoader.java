package service;

import exception.FilePathException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract public class CsvLoader<T> {
  static final  String COMMA_DELIMITER = ",";

  protected abstract T parseToObject(String[] line);
  protected abstract Integer getId(T obj);

  public Map<Integer, T> loadFromCsv(String fileName) throws FilePathException{
    List<String> stringLinesFromFile = new ArrayList<>();
    Map<Integer, T> map = new HashMap();
    try {
      stringLinesFromFile = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
      for (String line : stringLinesFromFile) {
        T obj = parseToObject(line.split(COMMA_DELIMITER));
        map.put(getId(obj), obj);
      }
    } catch (IOException e) {
      //System.out.println("IOException / wrong filepath: " + fileName + " ");
      throw new FilePathException("IOException / wrong filepath: " + fileName);
    }
    return map;
  }

}

