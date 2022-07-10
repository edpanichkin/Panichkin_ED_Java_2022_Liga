package ru.edpanichkin.tasktracker.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.edpanichkin.tasktracker.model.EntityType;
import ru.edpanichkin.tasktracker.repo.MemRepo;
@AllArgsConstructor
@Slf4j
public enum NoArgsMenuExecutor{

  EXIT("exit") {
    @Override
    public String execute() {
      System.exit(0);
      return "Shutdown the System";
    }
  },
  VIEW_ALL("view_all") {
    @Override
    public String execute() {
      return MemRepo.getMap(EntityType.USER).values().toString();
    }
  },
  FREE_MEMORY("free_memory") {
    @Override
    public String execute() {
      MemRepo.cleanMemoryData();
      return "Memory db was cleaned";
    }
  },
  LOAD_DATA("load_data") {
    @Override
    public String execute() {
      MemRepo.loadDataToProgram();
      return "DATA was loaded to Memory db";
    }
  },
  SAVE("SAVE") {
    @Override
    public String execute() {
      MemRepo.saveState();
      return "Данные сохранены";
    }
  };

  private final String status;
  public String getStatus() {
    return status;
  }
  public String execute() {
    return "??";
  }
}
