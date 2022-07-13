package ru.edpanichkin.tasktracker.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.edpanichkin.tasktracker.model.EntityType;
import ru.edpanichkin.tasktracker.repo.MemRepo;
import ru.edpanichkin.tasktracker.util.MessageUtil;

@AllArgsConstructor
@Slf4j
public enum NoArgsMenuExecutor {

  EXIT("exit") {
    @Override
    public String execute() {
      System.exit(0);
      return MessageUtil.shutDownMessage();
    }
  },
  VIEW_ALL("view_all") {
    @Override
    public String execute() {
      return MemRepo.getEntityMap(EntityType.USER).values().toString();
    }
  },
  FREE_MEMORY("free_memory") {
    @Override
    public String execute() {
      MemRepo.cleanMemoryData();
      return MessageUtil.cleanedMemoryMessage();
    }
  },
  LOAD_DATA("load_data") {
    @Override
    public String execute() {
      MemRepo.loadDataToProgram();
      return MessageUtil.loadDataMessage();
    }
  },
  SAVE("SAVE") {
    @Override
    public String execute() {
      MemRepo.saveState();
      return MessageUtil.saveDataMessage();
    }
  };

  private final String status;

  public String getStatus() {
    return status;
  }

  public String execute() {
    return "";
  }
}
