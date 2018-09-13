package tk.roydgar.wildfly.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tk.roydgar.wildfly.model.entity.User;
import tk.roydgar.wildfly.model.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<?> saveUser(User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }

    public void saveAllUsers(List<User> users) {
        userRepository.saveAll(users);
    }

    public ResponseEntity<?> updateUser(User user) {
        Optional<User> result = userRepository.update(user);
        return result.isPresent() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> deleteUserById(Long userId) {
        Optional<User> user = userRepository.deleteById(userId);
        return user.isPresent() ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }


}
