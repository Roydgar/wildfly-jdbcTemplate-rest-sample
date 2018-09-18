package tk.roydgar.wildfly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.roydgar.wildfly.model.entity.Token;
import tk.roydgar.wildfly.model.entity.form.LoginForm;
import tk.roydgar.wildfly.model.service.LoginService;

@RestController
@RequestMapping("/myLogin")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<Token> loginUser(@RequestBody LoginForm loginForm) {
        return loginService.login(loginForm);
    }

}
