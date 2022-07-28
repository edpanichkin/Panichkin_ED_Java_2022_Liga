package ru.edpanichkin.tasktracker.repo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import ru.edpanichkin.tasktracker.model.Task;
import ru.edpanichkin.tasktracker.model.Task_;
import ru.edpanichkin.tasktracker.model.User;
import ru.edpanichkin.tasktracker.model.User_;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.*;

@Repository
@RequiredArgsConstructor
public class UserCustomRepoImpl implements UserCustomRepo {

  private final EntityManager em;

  @Override
  public Object findUserWithMaxQuantityOfTasks(Specification<Task> specification) {
    //select users.id, users.user_name, count(t.user_id) as task_count from users join tasks t on users.id = t.user_id group by users.id order by task_count desc;
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Object> cq = cb.createQuery(Object.class);
    Root<Task> root = cq.from(Task.class);
    Join<Task, User> userJoin = root.join(Task_.user);
    cq.multiselect(userJoin.get(User_.id), userJoin.get(User_.userName), cb.count(userJoin)).groupBy(userJoin.get(User_.id)).orderBy(cb.desc(cb.count(userJoin)));
    Predicate predicate = specification.toPredicate(root, cq, cb);
    if (predicate != null) {
      cq.where(predicate);
    }
    Object userResult = null;
    try {
      userResult = em.createQuery(cq).setMaxResults(1).getSingleResult();
    } catch (NoResultException ignored) {
    }
    return userResult;
  }
}