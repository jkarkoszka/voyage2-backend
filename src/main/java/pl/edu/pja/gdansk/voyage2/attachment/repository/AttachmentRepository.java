package pl.edu.pja.gdansk.voyage2.attachment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.pja.gdansk.voyage2.attachment.domain.Attachment;

public interface AttachmentRepository extends MongoRepository<Attachment, String> {
}
