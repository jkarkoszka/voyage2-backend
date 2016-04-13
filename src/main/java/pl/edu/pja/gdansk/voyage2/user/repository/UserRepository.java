package pl.edu.pja.gdansk.voyage2.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.pja.gdansk.voyage2.user.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
}
