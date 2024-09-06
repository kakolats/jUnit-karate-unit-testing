package me.essejacques.demotestdeploy.controller;

import me.essejacques.demotestdeploy.entity.User;
import me.essejacques.demotestdeploy.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.findAllUsers()) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
       return ResponseEntity.ok(userService.fetchUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return  ResponseEntity.ok(userService.saveUser(user));
    }
}
