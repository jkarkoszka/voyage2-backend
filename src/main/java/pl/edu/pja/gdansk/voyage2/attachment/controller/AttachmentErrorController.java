package pl.edu.pja.gdansk.voyage2.attachment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.edu.pja.gdansk.voyage2.attachment.exception.AttachmentNotFoundException;
import pl.edu.pja.gdansk.voyage2.email.exception.SendEmailException;
import pl.edu.pja.gdansk.voyage2.error.domain.RestError;
import pl.edu.pja.gdansk.voyage2.error.domain.RestErrorCode;

@ControllerAdvice
public class AttachmentErrorController {

    @ExceptionHandler(AttachmentNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody RestError handleError(AttachmentNotFoundException exception) {
        RestError restError = new RestError(
                RestErrorCode.ATTACHMENT_NOT_FOUND,
                exception.getMessage(),
                "Attachment is not found"
        );
        return restError;
    }
}
