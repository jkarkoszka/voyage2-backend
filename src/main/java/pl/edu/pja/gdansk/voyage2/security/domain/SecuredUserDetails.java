package pl.edu.pja.gdansk.voyage2.security.domain;

import org.springframework.security.core.userdetails.User;

public class SecuredUserDetails extends User {

    public SecuredUserDetails(pl.edu.pja.gdansk.voyage2.user.domain.User user) {
        super(user.getEmail(), user.getPasswordHash(), user.getAuthorities());
    }
}
