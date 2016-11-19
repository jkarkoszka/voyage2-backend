package pl.edu.pja.gdansk.voyage2.error.domain;

public class RestError {

    private int code;
    private String message;
    private String developerMessage;

    public RestError(int code, String developerMessage, String message) {
        this.code = code;
        this.message = message;
        this.developerMessage = developerMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }
}
