package ru.edpanichkin.tasktracker.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.edpanichkin.tasktracker.model.EntityType;
import ru.edpanichkin.tasktracker.repo.MemRepo;

import java.util.Arrays;

@Slf4j
@AllArgsConstructor
public enum MenuCommands {
  EXIT("exit") {
    @Override
    public String doCommand() {
      System.exit(0);
      return "Shutdown the System";
    }
  },
  VIEW_ALL("view_all") {
    @Override
    public String doCommand() {
      return MemRepo.usersMap.values().toString();
    }
  },
  FREE_MEMORY("free_memory") {
    @Override
    public String doCommand() {
      MemRepo.cleanMemoryData();
      return "Memory db was cleaned";
    }
  },
  LOAD_DATA("load_data") {
    @Override
    public String doCommand() {
      MemRepo.loadDataToProgram();
      return "DATA was loaded to Memory db";
    }
  },
  EDIT("edit") {
    // 0     1  id  status
    //edit task 1     1
    @Override
    public String throwCommand(String[] command) {
      log.error("FROM MENU_COMMANDS " + Arrays.toString(command));
      return EntityFactory.getCommander(EntityType.valueOf(command[1].toUpperCase())).edit(command);
    }
  },
  DELETE("delete") {
    @Override
    public String throwCommand(String[] command) {
      log.error("FROM MENU_COMMANDS " + Arrays.toString(command));
      return EntityFactory.getCommander(EntityType.valueOf(command[1].toUpperCase())).delete(command);
    }
  },
  ADD("ADD") {
    @Override
    public String throwCommand(String[] command) {
      log.error("FROM MENU_COMMANDS " + Arrays.toString(command));
      return EntityFactory.getCommander(EntityType.valueOf(command[1].toUpperCase())).add(command);
    }
  },
  VIEW("VIEW") {
    @Override
    public String throwCommand(String[] command) {
      log.error("FROM MENU_COMMANDS " + Arrays.toString(command));
      String result = EntityFactory.getCommander(EntityType.valueOf(command[1].toUpperCase())).view(command);
      return result == null ? "" : result;
    }
  },
  SAVE("save") {
    @Override
    public String doCommand() {
      MemRepo.saveState();
      return "Данные сохранены";
    }
  };

  private final String status;

  public String getStatus() {
    return status;
  }

  public String doCommand() {
    return "??";
  }

  public String throwCommand(String[] command) {
    return Arrays.toString(command);
  }

  @Override
  public String toString() {
    return status;
  }
}