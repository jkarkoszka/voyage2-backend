package pl.edu.pja.gdansk.voyage2.storage.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.edu.pja.gdansk.voyage2.error.domain.RestError;
import pl.edu.pja.gdansk.voyage2.error.domain.RestErrorCode;
import pl.edu.pja.gdansk.voyage2.storage.service.exception.UploadFileException;

@ControllerAdvice
public class StorageErrorController {

    @ExceptionHandler(UploadFileException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody RestError handleError(UploadFileException exception) {
        RestError restError = new RestError(
                RestErrorCode.UPLOAD_FILE_FAILED,
                exception.getMessage(),
                "Upload file failed"
        );
        return restError;
    }
}
