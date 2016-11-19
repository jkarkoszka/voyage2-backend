package pl.edu.pja.gdansk.voyage2.email.service;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.email.exception.SendEmailException;

@Component
public class EmailService {

    @Autowired
    private SendGrid sendGrid;

    public SendGrid.Response sendEmail(SendGrid.Email email) {
        try {
            return sendGrid.send(email);
        } catch (SendGridException e) {
            throw new SendEmailException(e);
        }
    }
}
