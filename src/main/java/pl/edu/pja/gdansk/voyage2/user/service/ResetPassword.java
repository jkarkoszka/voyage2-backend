package pl.edu.pja.gdansk.voyage2.user.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.user.domain.PasswordStatus;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.exception.UserNotFoundException;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;
import pl.edu.pja.gdansk.voyage2.user.request.ResetPasswordRequest;

import java.util.Objects;

@Component
public class ResetPassword {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncryptor passwordEncryptor;
    @Autowired
    private NewPasswordSender newPasswordSender;

    public void reset(ResetPasswordRequest resetPasswordRequest) {
        String newPassword = RandomStringUtils.randomAlphabetic(7);
        User user = prepareUser(resetPasswordRequest, newPassword);
        newPasswordSender.send(user, newPassword);
        userRepository.save(user);
    }

    private User prepareUser(ResetPasswordRequest resetPasswordRequest, String newPassword) {
        User user = userRepository.findByEmail(resetPasswordRequest.getEmail());
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("User is not found by email = '" + resetPasswordRequest.getEmail() + "'");
        }
        user.setPasswordHash(passwordEncryptor.encrypt(newPassword));
        user.setPasswordStatus(PasswordStatus.ONETIME);
        return user;
    }
}
