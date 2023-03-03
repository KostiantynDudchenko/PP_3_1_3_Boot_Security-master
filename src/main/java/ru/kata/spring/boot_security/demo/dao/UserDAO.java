package ru.kata.spring.boot_security.demo.dao;



import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers();

    void save(User user);

    User show(Long id);

    void update(User user);

    void delete(Long id);

    User findByName(String name);
}
