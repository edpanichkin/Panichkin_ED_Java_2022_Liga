package ru.edpanichkin.tasktracker.service;

import ru.edpanichkin.tasktracker.model.EntityType;

public interface EntityHandler<T> {
  EntityType getType();

  String view(String[] command);

  String add(String[] command);

  String delete(String[] command);

  String edit(String[] command);
}
