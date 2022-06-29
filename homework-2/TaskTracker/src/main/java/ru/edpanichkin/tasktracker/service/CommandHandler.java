package ru.edpanichkin.tasktracker.service;

import lombok.AllArgsConstructor;
import ru.edpanichkin.tasktracker.model.MenuCommands;

import java.util.Arrays;
import java.util.stream.Collectors;

//@Component
@AllArgsConstructor
public class CommandHandler {

  private static String command;

  public CommandHandler(String command) {
    this.command = command;
  }

  public static String parseCommand() {
    String response = "ERROR: " + command + "\n" + Arrays.stream(MenuCommands.values()).collect(Collectors.toList());
    String[] commandArgs = command.split(" ");
    try {
      if (commandArgs.length == 1) {
        response = MenuCommands.valueOf(command.toUpperCase()).doCommand();
      } else {
        response = "MORE";
      }
    } catch (IllegalArgumentException ignored) {
    }
    return response;
  }

}
