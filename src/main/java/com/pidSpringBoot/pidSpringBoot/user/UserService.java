package com.pidSpringBoot.pidSpringBoot.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repository;
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        repository.findAll().forEach(users::add);

        return users;
    }

    public User findByLogin(String login){
        for (User user:repository.findAll()) {
            if(user.getLogin().equals(login)){
                return user;
            }
        }
        return null;

    }
    public Optional<User> getUser(String id) {
        int indice = Integer.parseInt(id);

        return repository.findById(indice);
    }
    public User findUser(String name){
        for (User user:repository.findAll()) {
            if(user.getLogin().equals(name)){
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        repository.save(user);
    }

    public void updateUser(String id, User user) {
        repository.save(user);
    }

    public void deleteUser(String id) {
        Long indice = (long) Integer.parseInt(id);

        repository.deleteById(Math.toIntExact(indice));
    }


}
