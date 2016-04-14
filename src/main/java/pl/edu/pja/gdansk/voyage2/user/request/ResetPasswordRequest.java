package pl.edu.pja.gdansk.voyage2.user.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ResetPasswordRequest {

    @NotNull
    @Size(min = 1)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
