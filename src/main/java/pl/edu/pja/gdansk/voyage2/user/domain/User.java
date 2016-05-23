package pl.edu.pja.gdansk.voyage2.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.edu.pja.gdansk.voyage2.attachment.domain.Attachment;
import pl.edu.pja.gdansk.voyage2.route.domain.Route;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Document
public class User {

    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    @Indexed(unique = true)
    private String email;
    @JsonIgnore
    private String passwordHash;
    private Long registerAt;
    private UserRole role = UserRole.USER;
    private boolean isPublic;
    private Attachment avatar;
    private PasswordStatus passwordStatus = PasswordStatus.NORMAL;
    private boolean isActive = false;
    @JsonIgnore
    private String activationToken;
    @JsonIgnore
    @DBRef
    private List<Route> favoriteRoutes = new ArrayList<>();

    public String getId() {
        return id;
    }

    public List<Route> getFavoriteRoutes() {
        return favoriteRoutes;
    }

    public void addRouteToFavoriteRoutes(Route route) {
        this.favoriteRoutes.add(route);
    }

    public void removeRouteFromFavoriteRoutes(Route route) {
        this.favoriteRoutes.remove(route);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Long getRegisterAt() {
        return registerAt;
    }

    public void setRegisterAt(Long registerAt) {
        this.registerAt = registerAt;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Collection<SimpleGrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(getRole().name()));
        return authorities;
    }

    public Attachment getAvatar() {
        return avatar;
    }

    public void setAvatar(Attachment avatar) {
        this.avatar = avatar;
    }

    public PasswordStatus getPasswordStatus() {
        return passwordStatus;
    }

    public void setPasswordStatus(PasswordStatus passwordStatus) {
        this.passwordStatus = passwordStatus;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getActivationToken() {
        return activationToken;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isPublic == user.isPublic &&
                isActive == user.isActive &&
                Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(passwordHash, user.passwordHash) &&
                Objects.equals(registerAt, user.registerAt) &&
                role == user.role &&
                Objects.equals(avatar, user.avatar) &&
                passwordStatus == user.passwordStatus &&
                Objects.equals(activationToken, user.activationToken) &&
                Objects.equals(favoriteRoutes, user.favoriteRoutes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, passwordHash, registerAt, role, isPublic, avatar, passwordStatus, isActive, activationToken, favoriteRoutes);
    }
}
