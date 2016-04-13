package pl.edu.pja.gdansk.voyage2.user.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterUserRequest {

    @NotNull
    @Size(min = 1)
    private String username;
    @NotNull
    @Size(min = 1)
    private String email;
    @NotNull
    @Size(min = 1)
    private String password;
    @NotNull
    private boolean isPublic;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
