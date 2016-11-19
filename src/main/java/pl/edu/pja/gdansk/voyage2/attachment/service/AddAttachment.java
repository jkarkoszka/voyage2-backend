package pl.edu.pja.gdansk.voyage2.attachment.service;

import org.apache.commons.io.FilenameUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pja.gdansk.voyage2.attachment.domain.Attachment;
import pl.edu.pja.gdansk.voyage2.attachment.repository.AttachmentRepository;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.storage.service.StorageService;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;

@Component
public class AddAttachment {

    @Autowired
    private StorageService storageService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;

    public Attachment add(SecuredUserDetails principal, MultipartFile multipartFile) {
        String attachmentId = generateAttachmentId(multipartFile);
        storageService.uploadFile(attachmentId, multipartFile);
        return saveAttachment(principal, multipartFile, attachmentId);
    }

    private Attachment saveAttachment(SecuredUserDetails principal, MultipartFile multipartFile, String attachmentId) {
        Attachment attachment = new Attachment();
        attachment.setId(attachmentId);
        attachment.setName(multipartFile.getOriginalFilename());
        attachment.setUser(userRepository.findByUsername(principal.getUsername()));
        return attachmentRepository.save(attachment);
    }

    private String generateAttachmentId(MultipartFile multipartFile) {
        return ObjectId.get().toString() + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
    }
}
