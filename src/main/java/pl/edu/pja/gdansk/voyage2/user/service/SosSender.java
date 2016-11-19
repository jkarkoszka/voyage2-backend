package pl.edu.pja.gdansk.voyage2.user.service;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.email.service.EmailService;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.exception.SosSenderException;
import pl.edu.pja.gdansk.voyage2.user.exception.UserHasNotSetSosEmailException;
import pl.edu.pja.gdansk.voyage2.user.exception.UserNotFoundException;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;
import pl.edu.pja.gdansk.voyage2.user.request.SosRequest;

import java.util.Objects;

@Component
public class SosSender {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;

    public void send(SecuredUserDetails principal, SosRequest sosRequest) {
        User user = userRepository.findByUsername(principal.getUsername());
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("User is not found by username = '" + principal.getUsername() + "'");
        }
        if (Objects.isNull(user.getSosEmail())) {
            throw new UserHasNotSetSosEmailException();
        }
        String[] to = {user.getSosEmail()};
        SendGrid.Email email = new SendGrid.Email();
        email.setFrom("\"Voyage Voyage\" <noreply@voyage2-staging.herokuapp.com>");
        email.setTo(to);
        email.setSubject("Voyage Voyage - Użytkownik " + user.getEmail() + " wzywa SOS");
        email.setHtml("Użytkownik " + user.getEmail() + " (" + user.getUsername() + ") nadaje sygnał SOS z pozycji: "
                + sosRequest.getPosition());
        SendGrid.Response response = emailService.sendEmail(email);
        if (response.getCode() != 200) {
            throw new SosSenderException("Sending email with SOS  for '" + user.getEmail() + " on email " + user.getSosEmail() +
                    " ' failed with status '" + response.getCode() + "' and message '" + response.getMessage() + "'");
        }
    }
}
