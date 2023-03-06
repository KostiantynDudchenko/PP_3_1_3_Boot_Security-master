package ru.kata.spring.boot_security.demo.dao;


import javax.persistence.*;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("select e from User e", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(entityManager.contains(getUserById(id)) ? getUserById(id) : entityManager.merge(getUserById(id)));
    }

    @Override
    public User findUserByName(String name) {
        TypedQuery<User> queryUser = entityManager.createQuery("select r from User r where r.name=:name",
                User.class).setParameter("name", name);
        return queryUser.getSingleResult();
    }
}
