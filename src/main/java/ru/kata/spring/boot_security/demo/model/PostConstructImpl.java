package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class PostConstructImpl {

    private final UserService userService;
    private final RoleService roleService;

    public PostConstructImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void runAfterObjectCreated() {

        User admin = new User();
        admin.setName("name1");
        admin.setSurname("lastname1");
        admin.setAge(33);
        admin.setPassword(new BCryptPasswordEncoder().encode("12345"));
        admin.addRole(new Role(1l, "ROLE_ADMIN"));

        User user = new User();
        user.setName("name2");
        user.setSurname("lastname2");
        user.setAge(44);
        user.setPassword(new BCryptPasswordEncoder().encode("12345"));
        user.addRole(new Role(2l, "ROLE_USER"));

        User user3 = new User();
        user3.setName("name3");
        user3.setSurname("lastname3");
        user3.setAge(45);
        user3.setPassword(new BCryptPasswordEncoder().encode("12345"));
        user3.addRole(new Role(3l, "ROLE_ADMIN"));
        user3.addRole(new Role(4l, "ROLE_USER"));

        userService.save(admin);
        userService.save(user);
        userService.save(user3);

    }
}

