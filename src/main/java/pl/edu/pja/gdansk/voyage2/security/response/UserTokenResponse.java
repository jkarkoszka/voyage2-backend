package pl.edu.pja.gdansk.voyage2.security.response;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.edu.pja.gdansk.voyage2.user.domain.PasswordStatus;

import java.util.ArrayList;
import java.util.Collection;

public class UserTokenResponse {

    private String token;
    private String username;
    private PasswordStatus passwordStatus;
    private Collection<String> authorities = new ArrayList<String>();

    public UserTokenResponse(String token, String username, PasswordStatus passwordStatus, Collection<SimpleGrantedAuthority> authorities) {
        this.token = token;
        this.username = username;
        this.passwordStatus = passwordStatus;
        for (GrantedAuthority authority: authorities) {
            this.authorities.add(authority.getAuthority().toString());
        }
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public Collection<String> getAuthorities() {
        return authorities;
    }

    public PasswordStatus getPasswordStatus() {
        return passwordStatus;
    }
}
