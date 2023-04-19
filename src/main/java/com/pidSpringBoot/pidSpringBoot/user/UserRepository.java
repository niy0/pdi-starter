package com.pidSpringBoot.pidSpringBoot.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Integer> {

    //@Query(value = "SELECT * FROM users u WHERE u.login = ?1 ", nativeQuery = true)
    User findByLogin(String login);
}
