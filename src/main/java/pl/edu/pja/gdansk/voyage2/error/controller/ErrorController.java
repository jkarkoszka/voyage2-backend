package pl.edu.pja.gdansk.voyage2.error.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.edu.pja.gdansk.voyage2.error.domain.RestError;
import pl.edu.pja.gdansk.voyage2.error.domain.RestErrorCode;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody RestError handleError(DuplicateKeyException exception) {
        RestError restError = new RestError(
                RestErrorCode.ITEM_ALREADY_EXISTS_IN_THE_DATABASE,
                exception.getMessage(),
                "Item already exist in the database"
        );
        return restError;
    }
}
