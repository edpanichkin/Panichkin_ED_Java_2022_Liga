package ru.edpanichkin.tasktracker.service;

import org.springframework.data.jpa.domain.Specification;
import ru.edpanichkin.tasktracker.model.Task;
import ru.edpanichkin.tasktracker.model.TaskStatus;
import ru.edpanichkin.tasktracker.model.Task_;

import java.time.LocalDate;

public class TasksSpecificationProvider {

  public static Specification<Task> getTasksSpecification(String type, LocalDate minDate, LocalDate maxDate) {
    Specification<Task> spec = Specification.where(null);

    if (maxDate != null) {
      spec = spec.and(dateLessThan(maxDate));
    }
    if (minDate != null) {
      spec = spec.and(dateGreaterThan(minDate));
    }
    if (type != null) {
      spec = spec.and(statusEquals(type));
    }
    return spec;
  }

  private static Specification<Task> statusEquals(String type) {
    return (root, query, cb) ->
            cb.equal(root.get(Task_.taskStatus), TaskStatus.values()[Integer.parseInt(type)]);
  }

  private static Specification<Task> dateLessThan(LocalDate dateTo) {
    return (root, query, cb) ->
            cb.lessThanOrEqualTo(root.get(Task_.date), dateTo);
  }

  private static Specification<Task> dateGreaterThan(LocalDate dateFrom) {
    return (root, query, cb) ->
            cb.greaterThanOrEqualTo(root.get(Task_.date), dateFrom);
  }
}
