package ru.edpanichkin.tasktracker.service;

import lombok.extern.slf4j.Slf4j;
import ru.edpanichkin.tasktracker.model.EntityType;
import ru.edpanichkin.tasktracker.service.entity.EntityFactory;
import ru.edpanichkin.tasktracker.util.MessageUtil;

@Slf4j
public enum NoArgsMenuExecutor {

    EXIT {
        @Override
        public String execute() {
            System.exit(0);
            return MessageUtil.shutDownMessage();
        }
    },
    VIEW_ALL {
        @Override
        public String execute() {
            return EntityFactory.getCommander(EntityType.MENU).view(new String[]{});
        }
    },
    FREE_MEMORY {
        @Override
        public String execute() {
            return EntityFactory.getCommander(EntityType.MENU).delete(new String[]{});
        }
    },
    LOAD_DATA {
        @Override
        public String execute() {
            return EntityFactory.getCommander(EntityType.MENU).add(new String[]{});
        }
    },
    SAVE {
        @Override
        public String execute() {
            return EntityFactory.getCommander(EntityType.MENU).edit(new String[]{});
        }
    };

    public String execute() {
        return "";
    }
}
