package ru.edpanichkin.tasktracker.repo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import ru.edpanichkin.tasktracker.dto.UserWithTasksDto;
import ru.edpanichkin.tasktracker.model.Task;
import ru.edpanichkin.tasktracker.model.Task_;
import ru.edpanichkin.tasktracker.model.User;
import ru.edpanichkin.tasktracker.model.User_;
import ru.edpanichkin.tasktracker.util.UserDtoMapper;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserCustomRepoImpl implements UserCustomRepo {

  private final EntityManager em;

  @Override
  public UserWithTasksDto findUserWithMaxQuantityOfTasks(Specification<Task> specification) {
    //select users.id, users.user_name, count(t.user_id) as task_count from users join tasks t on users.id = t.user_id group by users.id order by task_count desc;
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<User> cq = cb.createQuery(User.class);
    Root<Task> root = cq.from(Task.class);
    Join<Task, User> userJoin = root.join(Task_.user);
    cq.select(userJoin)
            .groupBy(userJoin.get(User_.id))
            .orderBy(cb.desc(cb.count(root.get(Task_.id))));
    Predicate predicate = specification.toPredicate(root, cq, cb);
    if (predicate != null) {
      cq.where(predicate);
    }
    User userResult = null;
    List<Task> taskList = new ArrayList<>();
    try {
      userResult = em.createQuery(cq).setMaxResults(1).getSingleResult();
      CriteriaQuery<Task> criteriaQuery = cb.createQuery(Task.class);
      Root<Task> rootTask = criteriaQuery.from(Task.class);
      predicate = specification.toPredicate(rootTask, criteriaQuery, cb);
      criteriaQuery.select(rootTask)
              .where(cb.equal(rootTask.get(Task_.user), userResult));
      if (predicate != null) {
        criteriaQuery.where(predicate);
      }
      taskList = em.createQuery(criteriaQuery).getResultList();
    } catch (NoResultException ignored) {
    }

    return UserDtoMapper.queryUserTaskToUserDto(userResult, taskList);
  }
}