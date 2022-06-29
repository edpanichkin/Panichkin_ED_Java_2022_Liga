package ru.edpanichkin.tasktracker.model;

import lombok.extern.slf4j.Slf4j;
import ru.edpanichkin.tasktracker.repository.MemoryRepository;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

@Slf4j
public enum MenuCommands {
  EXIT("exit") {
    @Override
    public String doCommand() {
      System.exit(0);
      return "Shutdown the System";
    }
  },
  SHOW_ALL("show_all") {
    @Override
    public String doCommand() {
      return MemoryRepository.usersMap.values().toString();
    }
  },
  FREE_MEMORY("free_memory") {
    @Override
    public String doCommand() {
      MemoryRepository.usersMap = new HashMap<>();
      MemoryRepository.tasksMap = new HashMap<>();
      return "Memory db was cleaned";
    }
  },
  LOAD_DATA("load_data") {
    @Override
    public String doCommand() {
      MemoryRepository.loadDataToProgram();
      return "DATA was loaded to Memory db";
    }
  },
  EDIT("EDIT") {
    @Override
    public String doCommand() {
      MemoryRepository.loadDataToProgram();
      return "DATA was loaded to Memory db";
    }
  },
  DELETE("DELETE") {
    @Override
    public String doCommand() {
      MemoryRepository.loadDataToProgram();
      return "DATA was loaded to Memory db";
    }
  },
  ADD("ADD") {
    @Override
    public String doCommand() {
      MemoryRepository.loadDataToProgram();
      return "DATA was loaded to Memory db";
    }
  },
  VIEW("VIEW") {
    @Override
    public String doCommand() {
      return "VIEW";
    }

    @Override
    public String throwCommand(String[] command) {
      log.error(Arrays.toString(command));
      switch (command[1].toLowerCase()) {
        case "task": return MemoryRepository.tasksMap.get(Integer.parseInt(command[2])).toString();
        case "user": return MemoryRepository.usersMap.get(Integer.parseInt(command[2]))
                .getTasksMapInUser()
                .values()
                .stream().sorted(Comparator.comparing(t -> t.getTaskStatus().ordinal()))
                .collect(Collectors.toList()).toString();
      }
      return null;
    }
  },
  SAVE_STATE("save_state") {
    @Override
    public String doCommand() {
      MemoryRepository.saveState();
      return "Данные сохранены";
    }
  };
  private final String status;


  MenuCommands(String status) {
    this.status = status;
  }

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
