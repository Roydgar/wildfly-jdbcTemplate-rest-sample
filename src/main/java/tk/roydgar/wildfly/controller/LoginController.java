package tk.roydgar.wildfly.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.roydgar.wildfly.model.entity.Token;
import tk.roydgar.wildfly.model.entity.form.LoginForm;
import tk.roydgar.wildfly.model.service.LoginService;

@RestController
@RequestMapping("/myLogin")
@Api(value = "Login", description = "Logins user in the system")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "Login user in the system and returns user's token")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Login success"),
                    @ApiResponse(code = 404, message = "User not found")
            }
    )
    @PostMapping
    public ResponseEntity<Token> loginUser(@RequestBody LoginForm loginForm) {
        return loginService.login(loginForm);
    }

}
