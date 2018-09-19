package tk.roydgar.wildfly.model.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
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

    @Transactional
    public ResponseEntity<Token> login(LoginForm loginForm) {
        Optional<User> userCandidate = userRepository.findByLogin(loginForm.getLogin());

        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            tokenRepository.deleteAll(user.getTokens());

            String randomToken = RandomStringUtils.random(16, true, true);

            if (passwordEncoder.matches(loginForm.getPassword(), user.getPassword())) {
                Token token = Token.builder()
                        .user(user)
                        .value(randomToken)
                        .build();

                tokenRepository.save(token);
                return ResponseEntity.ok(token);
            }
        }

        return ResponseEntity.notFound().build();
    }

}
