package pl.edu.pja.gdansk.voyage2.route.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.edu.pja.gdansk.voyage2.error.domain.RestError;
import pl.edu.pja.gdansk.voyage2.error.domain.RestErrorCode;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteAccessDeniedException;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteIdsNotMatchException;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteNotFoundException;

@ControllerAdvice
public class RouteErrorController {

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

    @ExceptionHandler(RouteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody RestError handleError(RouteNotFoundException exception) {
        RestError restError = new RestError(
                RestErrorCode.ROUTE_NOT_FOUND_IN_THE_DATABASE,
                exception.getMessage(),
                "Route wasn't found in the database"
        );
        return restError;
    }

    @ExceptionHandler(RouteIdsNotMatchException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody RestError handleError(RouteIdsNotMatchException exception) {
        RestError restError = new RestError(
                RestErrorCode.ROUTE_ID_FROM_PATH_DOES_NOT_MATCH_TO_THE_ONE_FROM_BODY,
                exception.getMessage(),
                "Route id from request path does not match to route id from request body"
        );
        return restError;
    }

    @ExceptionHandler(RouteAccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public @ResponseBody RestError handleError(RouteAccessDeniedException exception) {
        RestError restError = new RestError(
                RestErrorCode.ROUTE_ACCESS_DENIED,
                exception.getMessage(),
                "You have no rights to access to this route"
        );
        return restError;
    }
}
