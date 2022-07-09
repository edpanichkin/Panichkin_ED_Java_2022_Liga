//package ru.edpanichkin.tasktracker.service.reader;
//
//import org.junit.jupiter.api.Test;
//import ru.edpanichkin.tasktracker.model.Task;
//import ru.edpanichkin.tasktracker.model.TaskStatus;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class TaskCsvReaderTest {
//  static final String COMMA_DELIMITER = ",";
//  static final String COMMA_DELIMITER = ",";
//
//  static String file_good = "3,Читать Clean Code, изучить и применять принципы,2,10.07.2022,1";
//
//
//
//  @Test
//  void parseToObject() {
//    String[] line = file_good.split(COMMA_DELIMITER);
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//   Task task =  new Task(
//            Integer.parseInt(line[0]),
//            line[1].trim(),
//            line[2].trim(),
//            Integer.parseInt(line[3]),
//            LocalDate.parse(line[4].trim(), formatter),
//            line.length == MAX_CSV_VALUES ? TaskStatus.values()[Integer.parseInt(line[5])] : TaskStatus.NEW);
//  }
//
//  @Test
//  void getId() {
//  }
//}