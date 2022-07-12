package ru.edpanichkin.tasktracker.service;

import ru.edpanichkin.tasktracker.model.EntityType;

import java.util.Map;

public interface EntityHandler<T> {
  EntityType getType();

  String view(String[] command);

  String add(String[] command);

  String delete(String[] command);

  String edit(String[] command);

  Map<Integer, T> getMap();

}
