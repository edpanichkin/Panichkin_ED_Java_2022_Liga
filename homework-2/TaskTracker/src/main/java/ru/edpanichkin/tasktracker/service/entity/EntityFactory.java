package ru.edpanichkin.tasktracker.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.edpanichkin.tasktracker.model.EntityType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class EntityFactory {
  private static final Map<EntityType, EntityTypeHandler> entityTypeMap = new EnumMap<>(EntityType.class);

  @Autowired
  private EntityFactory(List<EntityTypeHandler> entityTypeHandlerList) {
    for (EntityTypeHandler entityTypeHandler : entityTypeHandlerList) {
      entityTypeMap.put(entityTypeHandler.getType(), entityTypeHandler);
    }
  }

  public static <T> EntityTypeHandler<T> getCommander(EntityType entityType) {
    EntityTypeHandler<T> entityTypeHandler = entityTypeMap.get(entityType);
    if (entityTypeHandler == null) {
      throw new IllegalArgumentException();
    }
    return entityTypeHandler;
  }
}
