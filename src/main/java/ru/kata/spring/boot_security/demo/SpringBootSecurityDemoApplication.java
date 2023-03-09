package ru.kata.spring.boot_security.demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Collections;

@SpringBootApplication
public class SpringBootSecurityDemoApplication implements ApplicationRunner {

    private final UserService userService;
    private final RoleService roleService;

    public SpringBootSecurityDemoApplication(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {


        userService.save(
                new User("Ivan", "Ivanov", 22,
                        new BCryptPasswordEncoder().encode("12345"),
                         Collections.singleton(roleService.getRoleByName("ROLE_ADMIN"))));
        userService.save(
                new User("Petr", "Petrov", 22,
                        new BCryptPasswordEncoder().encode("12345"),
                        Collections.singleton(roleService.getRoleByName("ROLE_USER"))));
    }
}
