package pl.edu.pja.gdansk.voyage2.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.user.domain.PasswordStatus;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.exception.AccountIsNotActiveException;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;
import pl.edu.pja.gdansk.voyage2.user.service.TimeService;

@Component
public class SecuredUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TimeService timeService;

    private static final Long TIME_FOR_CONDITIONALLY_ACTIVE_USER_ACCOUNT_IN_SECONDS = 3600L * 24L * 2L;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameAndPasswordStatusNot(username, PasswordStatus.EXPIRED);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        if (isNotAccountActiveAndConditionallyActive(user)) {
            throw new AccountIsNotActiveException();
        }
        return new SecuredUserDetails(user);
    }

    private boolean isNotAccountActiveAndConditionallyActive(User user) {
        return (!user.isActive() && !isAccountConditionallyActive(user));
    }

    private boolean isAccountConditionallyActive(User user) {
        return user.getRegisterAt() + TIME_FOR_CONDITIONALLY_ACTIVE_USER_ACCOUNT_IN_SECONDS >= timeService.getCurrentTimestamp();
    }
}
