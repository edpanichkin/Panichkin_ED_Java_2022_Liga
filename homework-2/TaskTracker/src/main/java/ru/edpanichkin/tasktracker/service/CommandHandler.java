package ru.edpanichkin.tasktracker.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class CommandHandler {

  private static final int NO_ARGS = 1;

  public static String parseCommand(String command) {
    String response = "ERROR: " + command +
            "\n" + Arrays.stream(NoArgsMenuExecutor.values()).collect(Collectors.toList()) +
            "\n" + Arrays.stream(ArgsMenuExecutor.values()).collect(Collectors.toList());
    String[] commandArgs = command.split("\\s+");
    try {
      if (commandArgs.length == NO_ARGS) {
        response = NoArgsMenuExecutor.valueOf(command.toUpperCase()).execute();
      }
      if (commandArgs.length > NO_ARGS) {
        log.error("Menu " + ArgsMenuExecutor.valueOf(commandArgs[0].toUpperCase()));
        response = ArgsMenuExecutor.valueOf(commandArgs[0].toUpperCase().trim()).throwCommand(commandArgs);
      }
    } catch (IllegalArgumentException ignored) {
      log.error(command);
      //response = ArgsMenuExecutor.valueOf(commandArgs[0]).throwCommand(commandArgs);
    }
    return response;
  }
}