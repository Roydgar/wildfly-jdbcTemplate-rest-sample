package tk.roydgar.wildfly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.roydgar.wildfly.model.entity.User;
import tk.roydgar.wildfly.model.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> findUser(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @GetMapping
    public ResponseEntity<?> findAllUsers() {
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/all")
    public void saveAllUsers(@RequestBody List<User> users) {
        userService.saveAllUsers(users);
    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId) {
        return userService.deleteUserById(userId);
    }

}
