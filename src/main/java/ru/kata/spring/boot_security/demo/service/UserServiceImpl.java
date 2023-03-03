package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }


    @Override
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    @Transactional
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public User show(Long id) {
        return userDAO.show(id);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDAO.delete(id);
    }

    @Override
    public User findByName(String name) {
        return userDAO.findByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findByName(username);
    }

    @PostConstruct
    private void postConstruct() {
        Role role_admin = new Role(1L, "ROLE_ADMIN");
        roleDAO.saveRole(role_admin);
        Role role_user = new Role(2L, "ROLE_USER");
        roleDAO.saveRole(role_user);

       /* User admin = new User();
        admin.setName("name1");
        admin.setSurname("lastname1");
        admin.setAge(33);
        admin.setPassword(bCryptPasswordEncoder.encode("12345"));
        admin.addRole(role_admin);

        User user = new User();
        user.setName("name2");
        user.setSurname("lastname2");
        user.setAge(44);
        user.setPassword(bCryptPasswordEncoder.encode("12345"));
        user.addRole(role_user);

        userDAO.save(admin);
        userDAO.save(user);*/
    }
}
