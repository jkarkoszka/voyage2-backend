package pl.edu.pja.gdansk.voyage2.security.domain;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class Session {

    private String token;
    private String username;
    private Collection<String> authorities = new ArrayList<String>();

    public Session(String token, String username, Collection<GrantedAuthority> authorities) {
        this.token = token;
        this.username = username;
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
}
