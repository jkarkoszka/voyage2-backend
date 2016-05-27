package pl.edu.pja.gdansk.voyage2.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.exception.UserNotFoundException;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;
import pl.edu.pja.gdansk.voyage2.user.request.ChangeIsActiveRequest;

@Component
public class ChangeIsActive {

    @Autowired
    private UserRepository userRepository;

    public void change(ChangeIsActiveRequest changeIsActiveRequest, SecuredUserDetails principal, String userId) {
        User user = userRepository.findByUsername(principal.getUsername());
        if (!userId.equals(user.getId())) {
            throw new UserNotFoundException("User is not found by id = '" + userId + "'");
        }
        user.setActive(changeIsActiveRequest.isActive());
        userRepository.save(user);
    }
}
