package pl.edu.pja.gdansk.voyage2.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.edu.pja.gdansk.voyage2.error.domain.RestError;
import pl.edu.pja.gdansk.voyage2.error.domain.RestErrorCode;
import pl.edu.pja.gdansk.voyage2.user.exception.*;

@ControllerAdvice
public class UserErrorController {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody RestError handleError(UserNotFoundException exception) {
        RestError restError = new RestError(
                RestErrorCode.USER_NOT_FOUND,
                exception.getMessage(),
                "User is not found"
        );
        return restError;
    }

    @ExceptionHandler(ActivationLinkSenderException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody RestError handleError(ActivationLinkSenderException exception) {
        RestError restError = new RestError(
                RestErrorCode.SENDING_ACTIVATION_LINK_FAILED,
                exception.getMessage(),
                "Sending an email with activation link failed"
        );
        return restError;
    }

    @ExceptionHandler(NewPasswordSenderException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody RestError handleError(NewPasswordSenderException exception) {
        RestError restError = new RestError(
                RestErrorCode.SENDING_NEW_PASSWORD_FAILED,
                exception.getMessage(),
                "Sending an email with new password failed"
        );
        return restError;
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody RestError handleError(EmailAlreadyUsedException exception) {
        RestError restError = new RestError(
                RestErrorCode.EMAIL_IS_ALREADY_USED,
                exception.getMessage(),
                "E-mail is already used"
        );
        return restError;
    }

    @ExceptionHandler(UsernameAlreadyUsedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody RestError handleError(UsernameAlreadyUsedException exception) {
        RestError restError = new RestError(
                RestErrorCode.USERNAME_IS_ALREADY_USED,
                exception.getMessage(),
                "Username is already used"
        );
        return restError;
    }

    @ExceptionHandler(AccountIsNotActiveException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody RestError handleError(AccountIsNotActiveException exception) {
        RestError restError = new RestError(
                RestErrorCode.ACCOUNT_IS_NOT_ACTIVE,
                exception.getMessage(),
                "Account is not active"
        );
        return restError;
    }
}
