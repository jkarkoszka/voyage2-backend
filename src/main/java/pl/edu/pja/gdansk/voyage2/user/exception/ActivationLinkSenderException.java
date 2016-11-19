package pl.edu.pja.gdansk.voyage2.user.exception;

public class ActivationLinkSenderException extends RuntimeException {

    public ActivationLinkSenderException() {
    }

    public ActivationLinkSenderException(String message) {
        super(message);
    }
}
