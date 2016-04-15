package pl.edu.pja.gdansk.voyage2.user.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.exception.UserNotFoundException;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;

import java.util.Objects;

@Component
public class ActivateUser {

    @Autowired
    private UserRepository userRepository;

    public void activate(String activationToken) {
        User user = userRepository.findByActivationToken(activationToken);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("User is not found by activationToken = '" + activationToken + "'");
        }
        user.setActive(true);
        user.setActivationToken(StringUtils.EMPTY);
        userRepository.save(user);
    }
}
