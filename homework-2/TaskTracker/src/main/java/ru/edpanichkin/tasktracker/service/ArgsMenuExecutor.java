package ru.edpanichkin.tasktracker.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.edpanichkin.tasktracker.model.EntityType;

import java.util.Arrays;

@Slf4j
@AllArgsConstructor
public enum ArgsMenuExecutor {
  EDIT("EDIT") {
    // 0     1  id  status
    //edit task 1     1
    //edit user 1 newName
    @Override
    public String throwCommand(String[] command) {
      log.error("FROM MENU_COMMANDS " + Arrays.toString(command));
      return necessaryArgLength(command.length, 4)
              ? EntityFactory.getCommander(EntityType.valueOf(command[1].toUpperCase())).edit(command)
              : "ERROR IN ARGS";
    }
  },
  DELETE("DELETE") {
    //delete task/user id
    @Override
    public String throwCommand(String[] command) {
      log.error("FROM MENU_COMMANDS " + Arrays.toString(command));
      return necessaryArgLength(command.length, 3)
              ? EntityFactory.getCommander(EntityType.valueOf(command[1].toUpperCase())).delete(command)
              : "ERROR IN ARGS";
    }
  },
  ADD("ADD") {
    @Override
    public String throwCommand(String[] command) {
      log.error("FROM MENU_COMMANDS " + Arrays.toString(command));
      String result = EntityFactory.getCommander(EntityType.valueOf(command[1].toUpperCase())).add(command);
      return result == null ? "" : result;
    }
  },
  VIEW("VIEW") {
    @Override
    public String throwCommand(String[] command) {
      log.error("FROM MENU_COMMANDS " + Arrays.toString(command));
      return necessaryArgLength(command.length, 3)
              ? EntityFactory.getCommander(EntityType.valueOf(command[1].toUpperCase())).view(command)
              : "ERROR IN ARGS";
    }
  };

  private final String status;

  public String getStatus() {
    return status;
  }

  public String execute() {
    return "??";
  }

  public String throwCommand(String[] command) {
    return Arrays.toString(command);
  }

  @Override
  public String toString() {
    return status;
  }

  public boolean necessaryArgLength(int length, int needLength) {

    return length == needLength;
  }
}