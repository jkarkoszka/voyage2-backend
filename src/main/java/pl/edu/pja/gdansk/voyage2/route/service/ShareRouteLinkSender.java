package pl.edu.pja.gdansk.voyage2.route.service;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.email.service.EmailService;
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.user.exception.ActivationLinkSenderException;

@Component
public class ShareRouteLinkSender {

    @Autowired
    private EmailService emailService;

    public void send(String emailAddress, Route route) {
        String[] to = {emailAddress};
        SendGrid.Email email = new SendGrid.Email();
        email.setFrom("\"Voyage Voyage\" <noreply@voyage2-staging.herokuapp.com>");
        email.setTo(to);
        email.setSubject("Voyage Voyage - Udostępniona trasa");
        String activationUrl = "voyage2://?routeId=" + route.getId();
        email.setHtml("Link do trasy:  <a href=\"" + activationUrl + "\" target=\"_blank\">" + activationUrl + "</a>");
        SendGrid.Response response = emailService.sendEmail(email);
        if (response.getCode() != 200) {
            throw new ActivationLinkSenderException("Sending email with shared route link for '" + emailAddress +
                    "' failed with status '" + response.getCode() + "' and message '" + response.getMessage() + "'");
        }
    }
}
