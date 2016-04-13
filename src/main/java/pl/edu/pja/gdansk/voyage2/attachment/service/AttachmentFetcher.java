package pl.edu.pja.gdansk.voyage2.attachment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.attachment.domain.Attachment;
import pl.edu.pja.gdansk.voyage2.attachment.repository.AttachmentRepository;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;

import java.util.List;

@Component
public class AttachmentFetcher {

    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Attachment> findByPrinicpal(SecuredUserDetails principal) {
        User user = userRepository.findByUsername(principal.getUsername());
        return attachmentRepository.findByUser(user);
    }
}
