package com.pidSpringBoot.pidSpringBoot.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Integer> {

    //@Query(value = "SELECT * FROM users u WHERE u.login = ?1 ", nativeQuery = true)
    User findByLogin(String login);
    List<User> findByLastName(String lastName);



    User findByEmail(String email);
}
