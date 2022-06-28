package ru.edpanichkin.tasktracker.util;

import ru.edpanichkin.tasktracker.repository.MemoryRepository;

public enum MenuCommands {

  SHOW_ALL("show_all") {
    @Override
    public String doCommand() {
      return MemoryRepository.usersMap.values().toString();
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

}
