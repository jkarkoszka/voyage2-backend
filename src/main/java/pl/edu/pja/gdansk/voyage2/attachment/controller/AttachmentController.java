package pl.edu.pja.gdansk.voyage2.attachment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pja.gdansk.voyage2.attachment.domain.Attachment;
import pl.edu.pja.gdansk.voyage2.attachment.service.AddAttachment;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;

@RestController
public class AttachmentController {

    @Autowired
    private AddAttachment addAttachment;

    @RequestMapping(value = "/attachments", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Attachment addAttachment(@RequestParam("file") MultipartFile multipartFile, @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        return addAttachment.add(principal, multipartFile);
    }
}
