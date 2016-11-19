package pl.edu.pja.gdansk.voyage2.user.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ChangePasswordRequest {

    @NotNull
    @Size(min = 1)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
