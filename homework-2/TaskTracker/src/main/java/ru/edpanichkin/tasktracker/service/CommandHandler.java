package ru.edpanichkin.tasktracker.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.edpanichkin.tasktracker.model.MenuCommands;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class CommandHandler {

  private static String command;

  public CommandHandler(String command) {
    this.command = command;
  }

  public static String parseCommand() {
    String response = "ERROR: " + command + "\n" + Arrays.stream(MenuCommands.values()).collect(Collectors.toList());
    String[] commandArgs = command.split("\\s+");
    //response = String.valueOf(commandArgs.length);
    try {
      if (commandArgs.length == 1) {
        response = MenuCommands.valueOf(command.toUpperCase()).doCommand();
      }
      if (commandArgs.length > 1) {
        response = MenuCommands.valueOf(commandArgs[0].toUpperCase()).throwCommand(commandArgs);
      }
    } catch (IllegalArgumentException ignored) {
      log.error(command);
      response = MenuCommands.valueOf(commandArgs[0]).throwCommand(commandArgs);
    }
    return response;
  }
}