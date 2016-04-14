package pl.edu.pja.gdansk.voyage2.user.service;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.user.domain.PasswordStatus;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.exception.ResetPasswordException;
import pl.edu.pja.gdansk.voyage2.user.exception.UserNotFound;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;
import pl.edu.pja.gdansk.voyage2.user.request.ResetPasswordRequest;

import java.util.Objects;

@Component
public class ResetPassword {

    @Autowired
    private SendGrid sendGrid;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncryptor passwordEncryptor;

    public void reset(ResetPasswordRequest resetPasswordRequest) {
        User user = userRepository.findByEmail(resetPasswordRequest.getEmail());
        if (Objects.isNull(user)) {
            throw new UserNotFound();
        }

        String newPassword = RandomStringUtils.randomAlphabetic(7);

        String[] to = {user.getEmail()};
        SendGrid.Email email = new SendGrid.Email();
        email.setFrom("\"Voyage Voyage\" <noreply@voyage2-staging.herokuapp.com>");
        email.setTo(to);
        email.setSubject("Voyage Voyage - Zresetowane hasło");
        email.setText("Nowe hasło to: " + newPassword);
        SendGrid.Response response = sendEmail(email);

        if (response.getCode() != 200) {
            throw new ResetPasswordException();
        }

        user.setPasswordHash(passwordEncryptor.encrypt(newPassword));
        user.setPasswordStatus(PasswordStatus.ONETIME);
        userRepository.save(user);
    }

    private SendGrid.Response sendEmail(SendGrid.Email email) {
        try {
            return sendGrid.send(email);
        } catch (SendGridException e) {
            throw new ResetPasswordException(e);
        }
    }
}
