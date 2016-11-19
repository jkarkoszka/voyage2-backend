package pl.edu.pja.gdansk.voyage2.folder.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.pja.gdansk.voyage2.folder.domain.Folder;
import pl.edu.pja.gdansk.voyage2.user.domain.User;

import java.util.List;

public interface FolderRepository extends MongoRepository<Folder, String> {

    List<Folder> findByUser(User user);

    Folder findByNameAndUser(String name, User user);

    Folder findByIdAndUser(String id, User user);
}
