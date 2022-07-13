package ru.edpanichkin.tasktracker.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.edpanichkin.tasktracker.model.EntityType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class EntityFactory {
  private static final Map<EntityType, EntityHandler> entityTypeMap = new EnumMap<>(EntityType.class);

  @Autowired
  private EntityFactory(List<EntityHandler> entityHandlerList) {
    for (EntityHandler entityHandler : entityHandlerList) {
      entityTypeMap.put(entityHandler.getType(), entityHandler);
    }
  }

  public static <T> EntityHandler<T> getCommander(EntityType entityType) {
    EntityHandler<T> entityHandler = entityTypeMap.get(entityType);
    if (entityHandler == null) {
      throw new IllegalArgumentException();
    }
    return entityHandler;
  }
}
