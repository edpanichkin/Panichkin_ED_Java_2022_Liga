package ru.edpanichkin.tasktracker.service.entity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.edpanichkin.tasktracker.model.EntityType;
import ru.edpanichkin.tasktracker.model.User;
import ru.edpanichkin.tasktracker.service.UserRepoService;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserEntityHandlerImpl implements EntityHandler<User> {

    private static final int ADD_USER_NAME_POS = 2;
    private static final int USER_ID_POS = 2;
    private static final int EDIT_USER_NAME_POS = 3;

    //  private static final Map<Integer, User> usersMap = MemRepo.getEntityMap(EntityType.USER);
    private final UserRepoService userRepoService;

    @Override
    public EntityType getType() {
        return EntityType.USER;
    }

    @Override
    public String view(String[] command) {
        int userId = Integer.parseInt(command[USER_ID_POS]);
        User user = userRepoService.getById(userId);
        return user == null ? "USER ID ERROR" : user.getId() + " " + user.getUserName() + " " +
                user.getTaskList()
                        .stream().sorted(Comparator.comparing(t -> t.getTaskStatus().ordinal()))
                        .collect(Collectors.toList());

    }

    @Override
    public String add(String[] command) {
        return null;
    }

    @Override
    public String delete(String[] command) {
        return null;
    }

    @Override
    public String edit(String[] command) {
        return null;
    }
}