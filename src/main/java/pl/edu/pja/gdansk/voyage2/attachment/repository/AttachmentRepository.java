package pl.edu.pja.gdansk.voyage2.attachment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.pja.gdansk.voyage2.attachment.domain.Attachment;
import pl.edu.pja.gdansk.voyage2.user.domain.User;

import java.util.List;

public interface AttachmentRepository extends MongoRepository<Attachment, String> {

    List<Attachment> findByUser(User user);
}
