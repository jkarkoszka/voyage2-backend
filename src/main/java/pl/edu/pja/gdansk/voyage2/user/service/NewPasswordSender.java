package pl.edu.pja.gdansk.voyage2.user.service;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.email.service.EmailService;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.exception.NewPasswordSenderException;

@Component
public class NewPasswordSender {

    @Autowired
    private EmailService emailService;

    public void send(User user, String newPassword) {
        String[] to = {user.getEmail()};
        SendGrid.Email email = new SendGrid.Email();
        email.setFrom("\"Voyage Voyage\" <noreply@voyage2-staging.herokuapp.com>");
        email.setTo(to);
        email.setSubject("Voyage Voyage - Reset hasła");
        email.setText("Nowe hasło to: " + newPassword);
        SendGrid.Response response = emailService.sendEmail(email);
        if (response.getCode() != 200) {
            throw new NewPasswordSenderException("Sending email with new password for '" + user.getEmail() +
                    "' failed with status '" + response.getCode() + "' and message '" + response.getMessage() + "'");
        }
    }
}
