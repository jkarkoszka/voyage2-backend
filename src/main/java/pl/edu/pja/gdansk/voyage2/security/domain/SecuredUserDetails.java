package pl.edu.pja.gdansk.voyage2.security.domain;

import org.springframework.security.core.userdetails.User;

import java.util.Objects;

public class SecuredUserDetails extends User {

    private pl.edu.pja.gdansk.voyage2.user.domain.User user;

    public SecuredUserDetails(pl.edu.pja.gdansk.voyage2.user.domain.User user) {
        super(user.getEmail(), user.getPasswordHash(), user.getAuthorities());
        this.user = user;
    }

    public pl.edu.pja.gdansk.voyage2.user.domain.User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) return false;
        SecuredUserDetails that = (SecuredUserDetails) o;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user);
    }
}
