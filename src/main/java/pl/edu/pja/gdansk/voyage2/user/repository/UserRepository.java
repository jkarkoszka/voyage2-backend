package pl.edu.pja.gdansk.voyage2.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.pja.gdansk.voyage2.user.domain.PasswordStatus;
import pl.edu.pja.gdansk.voyage2.user.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    User findByUsernameAndPasswordStatusNot(String username, PasswordStatus passwordStatus);
    User findByEmail(String email);
    User findByActivationToken(String activationToken);
}
