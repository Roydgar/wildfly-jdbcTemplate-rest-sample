package tk.roydgar.wildfly.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.roydgar.wildfly.model.entity.User;
import tk.roydgar.wildfly.model.entity.form.RegisterForm;
import tk.roydgar.wildfly.model.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<?> findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @Transactional
    public ResponseEntity<?> saveUser(User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }

    @Transactional
    public void saveAllUsers(List<User> users) {
        userRepository.saveAll(users);
    }

    @Transactional
    public ResponseEntity<?> updateUser(User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }

    @Transactional
    public ResponseEntity<?> deleteUser(User user) {
        userRepository.delete(user);
        return ResponseEntity.ok(user);
    }

    @Transactional
    public ResponseEntity<?> registerUser(RegisterForm registerForm) {
        User user = User
                        .builder()
                        .name(registerForm.getName())
                        .login(registerForm.getLogin())
                        .password(passwordEncoder.encode(registerForm.getPassword()))
                        .role(User.Role.USER)
                        .state(User.State.ACTIVE)
                        .build();

        return ResponseEntity.ok(userRepository.save(user));
    }

}
