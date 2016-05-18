package pl.edu.pja.gdansk.voyage2.folder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.edu.pja.gdansk.voyage2.error.domain.RestError;
import pl.edu.pja.gdansk.voyage2.error.domain.RestErrorCode;
import pl.edu.pja.gdansk.voyage2.folder.exception.FolderNameAlreadyUsedException;
import pl.edu.pja.gdansk.voyage2.folder.exception.FolderNotFoundException;
import pl.edu.pja.gdansk.voyage2.folder.exception.RouteIsNotInYourFolderException;

@ControllerAdvice
public class FolderErrorController {

    @ExceptionHandler(FolderNameAlreadyUsedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public
    @ResponseBody
    RestError handleError(FolderNameAlreadyUsedException exception) {
        RestError restError = new RestError(
                RestErrorCode.FOLDER_NAME_IS_ALREADY_USED,
                exception.getMessage(),
                "Folder name is already used"
        );
        return restError;
    }

    @ExceptionHandler(RouteIsNotInYourFolderException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public
    @ResponseBody
    RestError handleError(RouteIsNotInYourFolderException exception) {
        RestError restError = new RestError(
                RestErrorCode.ROUTE_IS_NOT_IN_YOUR_FOLDER,
                exception.getMessage(),
                "Route is not in your folder"
        );
        return restError;
    }


    @ExceptionHandler(FolderNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public
    @ResponseBody
    RestError handleError(FolderNotFoundException exception) {
        RestError restError = new RestError(
                RestErrorCode.FOLDER_NOT_FOUND,
                exception.getMessage(),
                "Folder is not found"
        );
        return restError;
    }
}
