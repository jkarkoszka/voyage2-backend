package pl.edu.pja.gdansk.voyage2.security.response;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.edu.pja.gdansk.voyage2.user.domain.PasswordStatus;

import java.util.ArrayList;
import java.util.Collection;

public class UserTokenResponse {

    private String id;
    private String token;
    private String username;
    private boolean isPublic;
    private PasswordStatus passwordStatus;
    private Collection<String> authorities = new ArrayList<String>();

    public UserTokenResponse(String id, String token, String username, boolean isPublic, PasswordStatus passwordStatus, Collection<SimpleGrantedAuthority> authorities) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.isPublic = isPublic;
        this.passwordStatus = passwordStatus;
        for (GrantedAuthority authority: authorities) {
            this.authorities.add(authority.getAuthority().toString());
        }
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public Collection<String> getAuthorities() {
        return authorities;
    }

    public PasswordStatus getPasswordStatus() {
        return passwordStatus;
    }
}
