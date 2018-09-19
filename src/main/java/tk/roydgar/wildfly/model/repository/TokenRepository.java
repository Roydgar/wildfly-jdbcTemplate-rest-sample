package tk.roydgar.wildfly.model.repository;

import org.springframework.data.repository.CrudRepository;
import tk.roydgar.wildfly.model.entity.Token;
import tk.roydgar.wildfly.model.entity.User;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token, Long> {

    Optional<Token> findByValue(String name);

}
