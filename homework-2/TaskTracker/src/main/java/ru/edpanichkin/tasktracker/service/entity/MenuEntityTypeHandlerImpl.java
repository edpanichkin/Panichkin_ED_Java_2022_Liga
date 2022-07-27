package ru.edpanichkin.tasktracker.service.entity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.edpanichkin.tasktracker.model.EntityType;
import ru.edpanichkin.tasktracker.service.DataInRepoService;
import ru.edpanichkin.tasktracker.util.MessageUtil;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuEntityTypeHandlerImpl implements EntityTypeHandler {
  private final DataInRepoService dataInRepoService;

  @Override
  public EntityType getType() {
    return EntityType.MENU;
  }

  @Override
  public String view(String[] command) {
    return dataInRepoService.viewAll().toString();
  }

  @Override
  public String add(String[] command) {
    return "CANNOT ADD FROM NO_ARGS MENU";
  }

  @Override
  public String delete(String[] command) {
    return dataInRepoService.clearState();
  }

  @Override
  public String edit(String[] command) {
    return dataInRepoService.saveState();
  }

  public String saveState() {
    return MessageUtil.saveDataMessage();
  }
}
