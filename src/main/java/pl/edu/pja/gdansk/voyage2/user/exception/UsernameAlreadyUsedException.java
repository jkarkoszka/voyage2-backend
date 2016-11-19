package pl.edu.pja.gdansk.voyage2.user.exception;

public class UsernameAlreadyUsedException extends RuntimeException {

    public UsernameAlreadyUsedException(String message) {
        super(message);
    }
}
