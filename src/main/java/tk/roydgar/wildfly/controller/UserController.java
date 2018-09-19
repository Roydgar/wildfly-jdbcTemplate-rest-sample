package tk.roydgar.wildfly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.roydgar.wildfly.model.entity.Token;
import tk.roydgar.wildfly.model.entity.User;
import tk.roydgar.wildfly.model.entity.form.LoginForm;
import tk.roydgar.wildfly.model.entity.form.RegisterForm;
import tk.roydgar.wildfly.model.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> findUser(
            @RequestHeader String token,
            @PathVariable Long userId) {
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

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterForm registerForm) {
        return userService.registerUser(registerForm);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        return userService.deleteUser(user);
    }

}
