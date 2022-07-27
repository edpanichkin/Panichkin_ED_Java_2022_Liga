package ru.edpanichkin.tasktracker.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.edpanichkin.tasktracker.model.EntityType;
import ru.edpanichkin.tasktracker.service.entity.EntityFactory;
import ru.edpanichkin.tasktracker.util.MessageUtil;

import java.util.Arrays;

@Slf4j
@Getter
public enum ArgsMenuExecutor {
  EDIT {
    final static int RIGHT_EDIT_COMMAND_LENGTH = 4;

    @Override
    public String throwCommand(String[] command) {
      return necessaryArgLength(command.length, RIGHT_EDIT_COMMAND_LENGTH)
              ? EntityFactory.getCommander(getEntityType(command)).edit(command)
              : MessageUtil.errorInArgs();
    }
  },
  DELETE {
    final static int RIGHT_DELETE_COMMAND_LENGTH = 3;

    @Override
    public String throwCommand(String[] command) {
      return necessaryArgLength(command.length, RIGHT_DELETE_COMMAND_LENGTH)
              ? EntityFactory.getCommander(getEntityType(command)).delete(command)
              : MessageUtil.errorInArgs();
    }
  },
  ADD {
    @Override
    public String throwCommand(String[] command) {
      String result = EntityFactory.getCommander(getEntityType(command)).add(command);
      return result == null ? "" : result;
    }
  },
  VIEW {
    final static int RIGHT_VIEW_COMMAND_LENGTH = 3;

    @Override
    public String throwCommand(String[] command) {
      return necessaryArgLength(command.length, RIGHT_VIEW_COMMAND_LENGTH)
              ? EntityFactory.getCommander(getEntityType(command)).view(command)
              : MessageUtil.errorInArgs();
    }
  };

  final static int ENTITY_COMMAND_POS = 1;

  private static EntityType getEntityType(String[] command) {
    return EntityType.valueOf(command[ENTITY_COMMAND_POS].toUpperCase());
  }

  public String throwCommand(String[] command) {
    return Arrays.toString(command);
  }

  public boolean necessaryArgLength(int length, int needLength) {
    return length == needLength;
  }
}