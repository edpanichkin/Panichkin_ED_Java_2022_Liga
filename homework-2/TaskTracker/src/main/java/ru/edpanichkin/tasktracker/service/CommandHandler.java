package ru.edpanichkin.tasktracker.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.edpanichkin.tasktracker.util.MenuCommands;

//@Component
@AllArgsConstructor
public class CommandHandler {

  private static String command;

  public CommandHandler(String command) {
    this.command = command;
  }

  public static String parseCommand() {
    return MenuCommands.valueOf(command.toUpperCase()).doCommand();
  }

}
