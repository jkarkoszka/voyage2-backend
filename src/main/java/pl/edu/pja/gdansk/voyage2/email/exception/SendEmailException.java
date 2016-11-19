package pl.edu.pja.gdansk.voyage2.email.exception;

import com.sendgrid.SendGridException;

public class SendEmailException extends RuntimeException {
    public SendEmailException(SendGridException cause) {
        super(cause);
    }
}
