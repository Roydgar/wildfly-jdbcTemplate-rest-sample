package tk.roydgar.wildfly.model.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tk.roydgar.wildfly.model.entity.Token;
import tk.roydgar.wildfly.model.entity.User;
import tk.roydgar.wildfly.model.entity.form.LoginForm;
import tk.roydgar.wildfly.model.repository.TokenRepository;
import tk.roydgar.wildfly.model.repository.UserRepository;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<Token> login(LoginForm loginForm) {
        Optional<User> userCandidate = userRepository.findByLogin(loginForm.getLogin());

        if (userCandidate.isPresent()) {
            User user = userCandidate.get();

            if (passwordEncoder.matches(loginForm.getPassword(), user.getPassword())) {
                Token token = Token.builder()
                        .user(user)
                        .value(RandomStringUtils.random(10, true, true))
                        .build();

                tokenRepository.save(token);
                return ResponseEntity.ok(token);
            }
        }

        return ResponseEntity.notFound().build();
    }

}
