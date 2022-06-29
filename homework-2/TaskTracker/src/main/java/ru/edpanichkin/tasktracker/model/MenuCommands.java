package ru.edpanichkin.tasktracker.model;

import ru.edpanichkin.tasktracker.repository.MemoryRepository;

import java.util.HashMap;

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

  @Override
  public String toString() {
    return "MenuCommands{" +
            "status='" + status + '\'' +
            '}';
  }
}
