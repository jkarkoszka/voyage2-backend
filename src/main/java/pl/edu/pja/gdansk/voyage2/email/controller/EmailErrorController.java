package pl.edu.pja.gdansk.voyage2.email.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.edu.pja.gdansk.voyage2.email.exception.SendEmailException;
import pl.edu.pja.gdansk.voyage2.error.domain.RestError;
import pl.edu.pja.gdansk.voyage2.error.domain.RestErrorCode;
import pl.edu.pja.gdansk.voyage2.user.exception.UserNotFoundException;

@ControllerAdvice
public class EmailErrorController {

    @ExceptionHandler(SendEmailException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody RestError handleError(SendEmailException exception) {
        RestError restError = new RestError(
                RestErrorCode.SENDING_EMAIL_FAILED,
                exception.getMessage(),
                "Sending an email failed"
        );
        return restError;
    }
}
