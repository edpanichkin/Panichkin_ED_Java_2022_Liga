package ru.edpanichkin.tasktracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edpanichkin.tasktracker.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>, UserCustomRepo {
}
