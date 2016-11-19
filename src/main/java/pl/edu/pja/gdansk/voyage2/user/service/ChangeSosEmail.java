package pl.edu.pja.gdansk.voyage2.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.user.domain.PasswordStatus;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.exception.UserNotFoundException;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;
import pl.edu.pja.gdansk.voyage2.user.request.ChangePasswordRequest;
import pl.edu.pja.gdansk.voyage2.user.request.ChangeSosEmailRequest;

@Component
public class ChangeSosEmail {

    @Autowired
    private UserRepository userRepository;

    public void change(ChangeSosEmailRequest changeSosEmailRequest, SecuredUserDetails principal, String userId) {
        User user = userRepository.findByUsername(principal.getUsername());
        if (!userId.equals(user.getId())) {
            throw new UserNotFoundException("User is not found by id = '" + userId + "'");
        }
        user.setSosEmail(changeSosEmailRequest.getEmail());
        userRepository.save(user);
    }
}
