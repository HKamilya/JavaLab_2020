package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    List<User> findAllByAge(Integer age);

    void updateByUsername(String login, String uuid, String password);

    Optional<User> findFirstByFirstnameAndLastname(String firstName, String lastName);

    User findByUsername(String username);

    Optional<User> findByUuid(String uuid);
}
