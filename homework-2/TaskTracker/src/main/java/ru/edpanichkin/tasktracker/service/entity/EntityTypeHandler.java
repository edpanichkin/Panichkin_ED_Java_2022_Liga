package ru.edpanichkin.tasktracker.service.entity;

import ru.edpanichkin.tasktracker.model.EntityType;

public interface EntityTypeHandler<T> {
  EntityType getType();

  String view(String[] command);

  String add(String[] command);

  String delete(String[] command);

  String edit(String[] command);

}
