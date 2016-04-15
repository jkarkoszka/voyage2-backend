package pl.edu.pja.gdansk.voyage2.user.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.domain.UserRole;
import pl.edu.pja.gdansk.voyage2.user.exception.EmailAlreadyUsedException;
import pl.edu.pja.gdansk.voyage2.user.exception.UsernameAlreadyUsedException;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;
import pl.edu.pja.gdansk.voyage2.user.request.RegisterUserRequest;

import java.util.Objects;

@Component
public class RegisterUser {

    @Autowired
    private ActivationLinkSender activationLinkSender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncryptor passwordEncryptor;

    public User createUser(RegisterUserRequest registerUserRequest) {
        if (Objects.nonNull(userRepository.findByUsername(registerUserRequest.getUsername()))) {
            throw new UsernameAlreadyUsedException("Username '" + registerUserRequest.getUsername() + "' is already used");
        }
        if (Objects.nonNull(userRepository.findByEmail(registerUserRequest.getEmail()))) {
            throw new EmailAlreadyUsedException("E-mail '" + registerUserRequest.getEmail() + "' is already used");
        }
        User user = prepareUser(registerUserRequest);
        activationLinkSender.send(user);
        return userRepository.save(user);
    }

    private User prepareUser(RegisterUserRequest registerUserRequest) {
        User user = new User();
        user.setUsername(registerUserRequest.getUsername());
        user.setEmail(registerUserRequest.getEmail());
        user.setPasswordHash(passwordEncryptor.encrypt(registerUserRequest.getPassword()));
        user.setRole(UserRole.USER);
        user.setPublic(registerUserRequest.isPublic());
        user.setActive(false);
        user.setActivationToken(RandomStringUtils.randomAlphabetic(16));
        return user;
    }
}
