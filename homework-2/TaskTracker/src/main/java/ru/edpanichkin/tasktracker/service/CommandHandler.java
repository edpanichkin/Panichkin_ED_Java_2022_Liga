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

  public static String parseCommand(String command) {
    String response = "ERROR: " + command + "\n" + Arrays.stream(MenuCommands.values()).collect(Collectors.toList());
    String[] commandArgs = command.split("\\s+");
    try {
      if (commandArgs.length == 1) {
        response = MenuCommands.valueOf(command.toUpperCase()).doCommand();
      }
      if (commandArgs.length > 1) {
        log.error("Menu " + MenuCommands.valueOf(commandArgs[0].toUpperCase()));
        response = MenuCommands.valueOf(commandArgs[0].toUpperCase().trim()).throwCommand(commandArgs);
      }
    } catch (IllegalArgumentException ignored) {
      log.error(command);
      response = MenuCommands.valueOf(commandArgs[0]).throwCommand(commandArgs);
    }
    return response;
  }
}